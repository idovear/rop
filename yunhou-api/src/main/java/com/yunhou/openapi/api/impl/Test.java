package com.yunhou.openapi.api.impl;

import java.util.Date;

import com.rop.RopRequest;
import com.rop.annotation.ServiceMethodBean;
import com.rop.security.SessionConstans;
import com.yunhou.openapi.api.TestInterface;
import com.yunhou.openapi.response.Foo;

@ServiceMethodBean(version = "1.0")
public class Test implements TestInterface {

    public Object test(RopRequest request) {
        return new Foo(request.getRopRequestContext().getSession().getAttribute(SessionConstans.USER_ID) + "",
                new Date().toLocaleString());
    }

}
