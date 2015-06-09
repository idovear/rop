package com.yunhou.openapi.dao.dataRedis;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.openapi.common.model.AppSecret;
import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.common.util.SerializeUtil;
import com.yunhou.openapi.dao.mysql.OauthApplicationDao;
import com.yunhou.openapi.model.oauth.OauthApplication;

/**
 * 
 * 应用授权数据交互中心<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 上午11:56:43
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class AppSecretData {
    @Autowired
    private OauthApplicationDao oauthApplicationDao;

    @Autowired
    private RedisClient redisClient;

    private static Logger logger = LoggerFactory.getLogger(AppSecretData.class);

    @PostConstruct
    public void init() {
        try {
            List<OauthApplication> selectAll = oauthApplicationDao.selectAll();
            if (selectAll != null && selectAll.size() > 0) {
                for (OauthApplication app : selectAll) {
                    AppSecret appSecret = new AppSecret();
                    appSecret.setAppKey(app.getAppKey());
                    appSecret.setAppSecret(app.getAppSecret());
                    appSecret.setLevel(app.getLevel());
                    appSecret.setRedirect_uri(app.getRedirectUri());
                    redisClient.put(RedisMark.REDIS_APP, app.getAppKey(), SerializeUtil.serialize(appSecret), -1);
                    if (app.getInvokeCount() >= 0) {
                        redisClient.put(RedisMark.REDIS_INVOKE_LIMIT, app.getAppKey(), app.getInvokeCount() + "", -1);
                    }else{
                        redisClient.del(RedisMark.REDIS_INVOKE_LIMIT, app.getAppKey());
                    }
                }
            }
            logger.info("初始化授权应用数据成功!");
        } catch (Exception e) {
            logger.error("初始化授权应用数据异常", e);
        }
    }

    public String getSecret(String appKey) {
        byte[] bs = redisClient.get(RedisMark.REDIS_APP, appKey);
        if (bs == null) {
            return null;
        }
        return ((AppSecret) SerializeUtil.unserialize(bs)).getAppSecret();
    }

    public String getRoleLevel(String appKey) {
        byte[] bs = redisClient.get(RedisMark.REDIS_APP, appKey);
        if (bs == null) {
            return null;
        }
        return ((AppSecret) SerializeUtil.unserialize(bs)).getLevel();
    }

    /* 更改操作 */
    public void put(OauthApplication app){
        AppSecret appSecret = new AppSecret();
        appSecret.setAppKey(app.getAppKey());
        appSecret.setAppSecret(app.getAppSecret());
        appSecret.setLevel(app.getLevel());
        appSecret.setRedirect_uri(app.getRedirectUri());
        redisClient.put(RedisMark.REDIS_APP, app.getAppKey(), SerializeUtil.serialize(appSecret), -1);
        if (app.getInvokeCount() >= 0) {
            redisClient.put(RedisMark.REDIS_INVOKE_LIMIT, app.getAppKey(), app.getInvokeCount() + "", -1);
        } else {
            redisClient.del(RedisMark.REDIS_INVOKE_LIMIT, app.getAppKey());
        }
    }

    public void del(OauthApplication app) {
        redisClient.del(RedisMark.REDIS_APP, app.getAppKey());
        redisClient.del(RedisMark.REDIS_INVOKE_LIMIT, app.getAppKey());
    }

}
