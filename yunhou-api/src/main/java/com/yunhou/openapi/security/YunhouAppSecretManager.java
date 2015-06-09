package com.yunhou.openapi.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.rop.security.AppSecretManager;
import com.yunhou.openapi.dao.dataRedis.AppSecretData;

/**
 * <pre>
 * 功能说明：应用管理
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class YunhouAppSecretManager implements AppSecretManager {

    @Autowired
    private AppSecretData appSecretData;

    public String getSecret(String appKey) {
        return appSecretData.getSecret(appKey);
    }

    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }

    public String getRoleLevel(String appKey) {
        return appSecretData.getRoleLevel(appKey);
    }
}
