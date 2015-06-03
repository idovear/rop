package com.yunhou.openapi.api.impl;

import com.rop.RopRequest;
import com.rop.annotation.ServiceMethodBean;
import com.yunhou.openapi.api.TestInterface;
import com.yunhou.openapi.response.Foo;

@ServiceMethodBean(version = "1.0")
public class Test implements TestInterface {

    public Object test(RopRequest request) {
        return new Foo();
    }

}
