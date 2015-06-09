package com.yunhou.openapi.dao.dataRedis;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.common.util.SerializeUtil;
import com.yunhou.openapi.dao.mysql.OauthLevelConfigDao;
import com.yunhou.openapi.model.oauth.OauthLevelConfig;

/**
 * 
 * 服务安全控制器数据<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月8日 上午10:47:21
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class ServiceAccessData {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private OauthLevelConfigDao configDao;

    private static Logger logger = LoggerFactory.getLogger(ServiceAccessData.class);

    @PostConstruct
    public void init() {
        try {
            List<OauthLevelConfig> configs = configDao.selectAll();
            if (configs != null && configs.size() > 0) {
                for (OauthLevelConfig config : configs) {
                    redisClient.put(RedisMark.REDIS_SERVICE_ACCESS, config.getLevel(),
                            SerializeUtil.serialize(config.getServiceMethods()), -1);
                }
            }
            logger.info("加载应用等级配置数据成功!");
        } catch (Exception e) {
            logger.error("加载应用等级配置数据异常", e);
        }
    }

    public List<String> getServices(String level) {
        byte[] bs = redisClient.get(RedisMark.REDIS_SERVICE_ACCESS, level);
        if (bs == null) {
            return null;
        }
        return (List<String>) SerializeUtil.unserialize(bs);
    }

    /* 变更操作 */
    public void put(String level, List<String> methods) {
        if (methods == null || methods.size() == 0) {
            redisClient.del(RedisMark.REDIS_SERVICE_ACCESS, level);
        } else {
            redisClient.put(RedisMark.REDIS_SERVICE_ACCESS, level, SerializeUtil.serialize(methods), -1);
        }
    }

    public void del(String level) {
        redisClient.del(RedisMark.REDIS_SERVICE_ACCESS, level);
    }

}
