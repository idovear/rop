/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-7-30
 */
package com.rop.security;

import com.rop.RopRequestContext;

/**
 * <pre>
 * 默认的实现
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class DefaultInvokeTimesController implements InvokeTimesController {

    public void caculateInvokeTimes(String appKey, RopRequestContext requestContext) {
        // TODO Auto-generated method stub

    }

    public boolean isUserInvokeLimitExceed(String appKey, String userId, String method) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isSessionInvokeLimitExceed(String appKey, String sessionId, String method) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isAppInvokeLimitExceed(String appKey, String method) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isAppInvokeFrequencyExceed(String appKey, String method) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isIpInvokeLimitExceed(String ip, String method) {
        // TODO Auto-generated method stub
        return false;
    }

}
