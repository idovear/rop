package com.yunhou.openapi.dao.dataRedis;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.dao.mysql.OauthLimitDao;
import com.yunhou.openapi.model.oauth.OauthLimit;
import com.yunhou.openapi.model.oauth.OauthlimitTime;
import com.yunhou.openapi.model.security.RatelimtResource;

/**
 * 
 * 服务访问次数和频率限制数据中心<br/>
 * 1 调用频率限制 2 调用次数限制
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 上午10:44:55
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class InvokeTimesData {

    private static String keySplit = ":";

    @Autowired
    private OauthLimitDao limitDao;

    @Autowired
    private RedisClient redisClient;

    private static Logger logger = LoggerFactory.getLogger(InvokeTimesData.class);

    @PostConstruct
    public void init() {
        try {
            List<OauthLimit> limits = limitDao.selectAll();
            if (limits != null && limits.size() > 0) {
                for (OauthLimit oauthLimit : limits) {
                    redisClient.put(RedisMark.REDIS_INVOKE_FRE,
                            getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()),
                            oauthLimit.getLimitCount() + "", -1);
                    redisClient.put(RedisMark.REDIS_INVOKE_FRE_UNIT,
                            getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()), oauthLimit
                                    .getLimitTime().name(), -1);
                }
            }
            logger.info("初始化访问次数和频率数据成功!");
        } catch (Exception e) {
            logger.error("初始化访问次数和频率限制数据异常", e);
        }
    }

    /**
     * 
     * 统计应用访问次数. <br/>
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月3日 下午3:09:18
     * @version 1.0
     * 
     * @param appkey
     */
    public void caculateAppInvokeCount(String appkey) {
        redisClient.incr(RedisMark.REDIS_INVOKE_LIMIT_COUNT, appkey);
    }

    public boolean isAppInvokeLimitExceed(String appkey) {
        String limitCount = redisClient.getToString(RedisMark.REDIS_INVOKE_LIMIT, appkey);
        if (limitCount != null) {
            String count = redisClient.getToString(RedisMark.REDIS_INVOKE_LIMIT_COUNT, appkey);
            long appCount = 0;
            if (count != null) {
                appCount = Long.parseLong(count);
            }
            return appCount > Long.parseLong(limitCount);
        }
        return false;
    }

    /**
     * 
     * 统计接口调用频率. <br/>
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
        if (StringUtils.isBlank(resource)) {
            return;
        }
        String key = getKey(ratelimtResource, method, roleGroup);
        String oauthTime = redisClient.getToString(RedisMark.REDIS_INVOKE_FRE_UNIT, key);
        if (oauthTime != null) {
            key = key + keySplit + resource;
            redisClient.put(RedisMark.REDIS_INVOKE_FRE_COUNT, key + UUID.randomUUID().toString(), 1 + "",
                    OauthlimitTime.valueOf(oauthTime).getSec());
        }
    }

    public boolean isInvokeFrequencyExceed(RatelimtResource ratelimtResource, String resource, String method, String roleGroup) {
        if (StringUtils.isBlank(resource)) {
            return false;
        }
        String key = getKey(ratelimtResource, method, roleGroup);
        Object limitCount = redisClient.getToString(RedisMark.REDIS_INVOKE_FRE, key);
        if (limitCount != null) {
            return redisClient.count(RedisMark.REDIS_INVOKE_FRE_COUNT, key + keySplit + resource + "*") >= Long
                    .parseLong(limitCount + "");
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

    /* 更改操作 */
    public void put(OauthLimit oauthLimit) {
        redisClient.put(RedisMark.REDIS_INVOKE_FRE,
                getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()), oauthLimit.getLimitCount() + "",
                -1);
        redisClient.put(RedisMark.REDIS_INVOKE_FRE_UNIT,
                getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()),
                oauthLimit.getLimitTime().name(), -1);
    }

    public void del(OauthLimit oauthLimit) {
        redisClient.del(RedisMark.REDIS_INVOKE_FRE,
                getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()));
        redisClient.del(RedisMark.REDIS_INVOKE_FRE_UNIT,
                getKey(oauthLimit.getResource(), oauthLimit.getMethod(), oauthLimit.getLevel()));
    }

}
