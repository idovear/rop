package com.yunhou.openapi.dao.dataRedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rop.security.AppSecret;
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
    private static Map<String, AppSecret> appKeySecretMap = new HashMap<String, AppSecret>();

    @Autowired
    private OauthApplicationDao oauthApplicationDao;

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
                    appSecret.setLevel(app.getLevel().name());
                    appKeySecretMap.put(app.getAppKey(), appSecret);
                }
            }
            logger.info("初始化授权应用数据成功!");
        } catch (Exception e) {
            logger.error("初始化授权应用数据异常", e);
        }
    }

    public String getSecret(String appKey) {
        if (!appKeySecretMap.containsKey(appKey)) {
            return null;
        }
        return appKeySecretMap.get(appKey).getAppSecret();
    }

    public String getRoleLevel(String appKey) {
        if (!appKeySecretMap.containsKey(appKey)) {
            return null;
        }
        return appKeySecretMap.get(appKey).getLevel();
    }

}
