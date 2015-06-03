package com.yunhou.openapi.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequestContext;
import com.rop.security.InvokeTimesController;
import com.rop.security.SessionConstans;
import com.yunhou.openapi.dao.dataRedis.AppSecretData;
import com.yunhou.openapi.dao.dataRedis.InvokeTimesData;
import com.yunhou.openapi.model.security.RatelimtResource;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class YunhouInvokeTimesController implements InvokeTimesController {

    @Autowired
    private InvokeTimesData invokeTimes;

    @Autowired
    private AppSecretData appSecretData;

    public void caculateInvokeTimes(String appKey, RopRequestContext req) {
        invokeTimes.caculateAppInvokeCount(appKey);
        String roleLevel = appSecretData.getRoleLevel(appKey);
        invokeTimes.caculateInvokeTimes(RatelimtResource.APP_KEY, appKey, req.getMethod(), roleLevel);
        invokeTimes.caculateInvokeTimes(RatelimtResource.IP, req.getIp(), req.getMethod(), roleLevel);
        invokeTimes.caculateInvokeTimes(RatelimtResource.USER_ID, req.getSession().getAttribute(SessionConstans.USER_ID) + "",
                req.getMethod(), roleLevel);
        invokeTimes.caculateInvokeTimes(RatelimtResource.APP_KEY, appKey, "*", roleLevel);
        invokeTimes.caculateInvokeTimes(RatelimtResource.IP, req.getIp(), "*", roleLevel);
        invokeTimes.caculateInvokeTimes(RatelimtResource.USER_ID, req.getSession().getAttribute(SessionConstans.USER_ID) + "",
                "*", roleLevel);
    }

    /* 总次数限制 */
    public boolean isAppInvokeLimitExceed(String appKey) {
        return invokeTimes.isAppInvokeLimitExceed(appKey);
    }

    /* 频率限制 */
    public boolean isAppInvokeFrequencyExceed(String appKey, String method) {
        return invokeTimes.isInvokeFrequencyExceed(RatelimtResource.APP_KEY, appKey, method, appSecretData.getRoleLevel(appKey))
                || invokeTimes.isInvokeFrequencyExceed(RatelimtResource.APP_KEY, appKey, "*", appSecretData.getRoleLevel(appKey));
    }

    public boolean isUserInvokeFrequencyExceed(String appKey, String userId, String method) {
        return invokeTimes.isInvokeFrequencyExceed(RatelimtResource.USER_ID, userId, method, appSecretData.getRoleLevel(appKey))
                || invokeTimes.isInvokeFrequencyExceed(RatelimtResource.USER_ID, userId, "*", appSecretData.getRoleLevel(appKey));
    }

    public boolean isIpInvokeFrequencyExceed(String appKey, String ip, String method) {
        return invokeTimes.isInvokeFrequencyExceed(RatelimtResource.IP, ip, method, appSecretData.getRoleLevel(appKey))
                || invokeTimes.isInvokeFrequencyExceed(RatelimtResource.IP, ip, "*", appSecretData.getRoleLevel(appKey));
    }
}
