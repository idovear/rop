/**
 * Copyright：中软海晟信息科技有限公司 版权所有 违者必究 2014 
 */
package com.yunhou.openapi.api;

import com.rop.RopRequest;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;
import com.yunhou.openapi.request.LogonRequest;

/**
 * @author : chenxh(quickselect@163.com)
 * @date: 14-3-6
 */
public interface UserServiceInterface {

    @ServiceMethod(method = "user.getSession", version = "1.0", needAccessToken = NeedAccessTokenType.NO)
    Object getSession(LogonRequest request);

    @ServiceMethod(method = "user.logon", version = "1.0", needAccessToken = NeedAccessTokenType.NO)
    Object logon(LogonRequest request);

}
