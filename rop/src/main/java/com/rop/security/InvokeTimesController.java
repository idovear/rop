/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-7-30
 */
package com.rop.security;

import com.rop.RopRequestContext;
import com.rop.session.Session;

/**
 * <pre>
 * 服务访问次数及频率的控制管理器
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public interface InvokeTimesController {

    /**
     * 计算应用、会话及用户服务调度总数
     * 
     * @param appKey
     * @param session
     */
    void caculateInvokeTimes(String appKey, RopRequestContext requestContext);

    /**
     * 用户服务访问次数是否超限
     * 
     * @param session
     * @return
     */
    boolean isUserInvokeLimitExceed(String appKey, String userId, String method);

    /**
     * 某个会话的服务访问次数是否超限
     * 
     * @param sessionId
     * @return
     */
    boolean isSessionInvokeLimitExceed(String appKey, String sessionId, String method);

    /**
     * 应用的服务访问次数是否超限
     * 
     * @param appKey
     * @return
     */
    boolean isAppInvokeLimitExceed(String appKey, String method);

    /**
     * 应用对服务的访问频率是否超限
     * 
     * @param appKey
     * @return
     */
    boolean isAppInvokeFrequencyExceed(String appKey, String method);

    /**
     * 
     * ip访问权限限制. <br/>
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月1日 上午9:15:27
     * @version 1.0
     * 
     * @param ip
     * @return
     */
    boolean isIpInvokeLimitExceed(String ip, String method);
}
