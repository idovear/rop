package com.yunhou.openapi.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequestContext;
import com.rop.security.AppSecretManager;
import com.rop.security.InvokeTimesController;
import com.rop.security.SessionConstans;
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
    private AppSecretManager appSecretManager;

    public void caculateInvokeTimes(String appKey, RopRequestContext req) {
        String userId = "";
        if (req.getSession() != null && req.getSession().getAttribute(SessionConstans.USER_ID) != null) {
            userId = req.getSession().getAttribute(SessionConstans.USER_ID) + "";
        }
        invokeTimes.caculateInvokeTimes(RatelimtResource.APP_KEY, appKey, req.getMethod(), "");
        invokeTimes.caculateInvokeTimes(RatelimtResource.IP, req.getIp(), req.getMethod(), "");
        invokeTimes.caculateInvokeTimes(RatelimtResource.USER_ID, userId, req.getMethod(), "");
    }

    public boolean isUserInvokeLimitExceed(String appKey, String userId, String method) {
        return invokeTimes.isInvokeLimitExceed(RatelimtResource.USER_ID, userId, method, "");
    }

    public boolean isSessionInvokeLimitExceed(String appKey, String sessionId, String method) {
        return false;
    }

    public boolean isAppInvokeLimitExceed(String appKey, String method) {
        return invokeTimes.isInvokeLimitExceed(RatelimtResource.APP_KEY, appKey, method, appSecretManager.getSecret(appKey));
    }

    public boolean isAppInvokeFrequencyExceed(String appKey, String method) {
        return false;
    }

    public boolean isIpInvokeLimitExceed(String ip, String method) {
        return invokeTimes.isInvokeLimitExceed(RatelimtResource.IP, ip, method, "");
    }
}
