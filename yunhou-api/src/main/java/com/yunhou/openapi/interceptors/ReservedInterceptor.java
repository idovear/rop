/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-4-26
 */
package com.yunhou.openapi.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rop.AbstractInterceptor;
import com.rop.RopRequestContext;
import com.rop.security.MainError;
import com.yunhou.openapi.dao.dataRedis.InterceptorData;

/**
 * <pre>
 *    该拦截器仅对method为“user.add”进行拦截，你可以在{@link #isMatch(com.rop.RopRequestContext)}方法中定义拦截器的匹配规则。
 *  你可以通过{@link com.rop.RopRequestContext#getServiceMethodDefinition()}获取服务方法的注解信息，通过这些信息进行拦截匹配规则
 *  定义。
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */

@Component
public class ReservedInterceptor extends AbstractInterceptor {
    @Autowired
    private InterceptorData interceptorData;

    private static Logger logger = LoggerFactory.getLogger(ReservedInterceptor.class);

    /**
     * 在数据绑定后，服务方法调用前执行该拦截方法
     * 
     * @param ropRequestContext
     */
    public void beforeService(RopRequestContext req) {
        logger.info("拦截 appkey:" + req.getAppKey() + " ip:" + req.getIp() + " token:" + req.getAccess_token());
        // 设置了RopResponse后，后续的服务将不执行，直接返回这个RopResponse响应
        MainError interceptor = interceptorData.isInterceptor(req);
        if (interceptor != null) {
            req.setRopResponse(interceptor);
        }
    }

    /**
     * 在服务执行完成后，响应返回前执行该拦截方法
     * 
     * @param ropRequestContext
     */

    public void beforeResponse(RopRequestContext ropRequestContext) {

    }

    /**
     * 对method为user.add的方法进行拦截，你可以通过methodContext中的信息制定拦截方案
     * 
     * @param ropRequestContext
     * @return
     */

    public boolean isMatch(RopRequestContext ropRequestContext) {
        return interceptorData.isMatch(ropRequestContext);
    }
}
