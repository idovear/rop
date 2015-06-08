package com.yunhou.openapi.request.config;

import com.rop.AbstractRopRequest;
import com.yunhou.openapi.model.security.RatelimtResource;

public class OauthInterceptorRequest extends AbstractRopRequest {
    private long id;
    private RatelimtResource type;// 拦截类型 ip,应用..
    private String resource;// 拦截匹配字符串

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatelimtResource getType() {
        return type;
    }

    public void setType(RatelimtResource type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
