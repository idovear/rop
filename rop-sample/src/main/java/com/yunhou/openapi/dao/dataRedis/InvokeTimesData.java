package com.yunhou.openapi.dao.dataRedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.openapi.dao.mysql.OauthLimitDao;
import com.yunhou.openapi.model.oauth.OauthLimit;
import com.yunhou.openapi.model.security.RatelimtResource;

/**
 * 
 * 服务访问次数和频率限制数据中心<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 上午10:44:55
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class InvokeTimesData {

    private static String keySplit = "-";

    private static Map<String, Integer> limitsMap = new HashMap<String, Integer>();// 次数配置
    private static Map<String, Integer> countsMap = new HashMap<String, Integer>(); // 调用次数统计

    @Autowired
    private OauthLimitDao limitDao;

    private static Logger logger = LoggerFactory.getLogger(InvokeTimesData.class);

    @PostConstruct
    public void init() {
        try {
            List<OauthLimit> limits = limitDao.selectAll();
            if (limits != null && limits.size() > 0) {
                for (OauthLimit oauthLimit : limits) {
                    limitsMap.put(getKey(oauthLimit.getResource(), oauthLimit.getMethod(), ""), oauthLimit.getLimitCount());
                }
            }
            logger.info("初始化访问次数和频率数据成功!");
        } catch (Exception e) {
            logger.error("初始化访问次数和频率限制数据异常", e);
        }
    }

    /**
     * 
     * 统计接口调用次数. <br/>
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月1日 下午3:57:07
     * @version 1.0
     * 
     * @param ratelimtResource 来源类型
     * @param resource 来源字符串
     * @param method 方法名
     * @param roleGroup 权限组 当为空的时候，默认最低权限组
     */
    public void caculateInvokeTimes(RatelimtResource ratelimtResource, String resource, String method, String roleGroup) {
        if (StringUtils.isNotBlank(resource)) {
            return;
        }
        String key = getKey(ratelimtResource, method, roleGroup);
        if (limitsMap.containsKey(key)) {
            key = key + keySplit + resource;
            if (!countsMap.containsKey(key)) {
                countsMap.put(key, 0);
            }
            countsMap.put(key, countsMap.get(key) + 1);
        }
    }

    public boolean isInvokeLimitExceed(RatelimtResource ratelimtResource, String resource, String method, String roleGroup) {
        if (StringUtils.isBlank(resource)) {
            return false;
        }
        String key = getKey(ratelimtResource, method, roleGroup);
        if (limitsMap.containsKey(key)) {
            int count = 0;
            if (countsMap.containsKey(key + keySplit + resource)) {
                count = countsMap.get(key + keySplit + resource);
            }
            return count >= limitsMap.get(key);
        }
        return false;
    }

    public static String getKey(RatelimtResource ratelimtResource, String method, String roleGroup) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(ratelimtResource.name());
        buffer.append(keySplit);
        buffer.append(method);
        if (StringUtils.isNotBlank(roleGroup)) {
            buffer.append(keySplit);
            buffer.append(roleGroup);
        }
        return buffer.toString();
    }

}
