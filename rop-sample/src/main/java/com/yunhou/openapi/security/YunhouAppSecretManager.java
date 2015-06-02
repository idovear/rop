package com.yunhou.openapi.security;

/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-5-25
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.rop.security.AppSecretManager;
import com.yunhou.openapi.dao.dataRedis.AppSecretData;

/**
 * <pre>
 * 功能说明：
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
