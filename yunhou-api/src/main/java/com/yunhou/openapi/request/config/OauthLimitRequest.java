package com.yunhou.openapi.request.config;

import com.rop.AbstractRopRequest;
import com.yunhou.openapi.model.oauth.OauthApplicationLevel;
import com.yunhou.openapi.model.oauth.OauthlimitTime;
import com.yunhou.openapi.model.security.RatelimtResource;

public class OauthLimitRequest extends AbstractRopRequest {
    private long id;
    private RatelimtResource resource;// 来源
    private String method;// 方法
    private int limitCount;// 限制次数
    private OauthlimitTime limitTime;// 限制次数 时间单位
    private OauthApplicationLevel level;// 应用等级

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatelimtResource getResource() {
        return resource;
    }

    public void setResource(RatelimtResource resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }

    public OauthlimitTime getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(OauthlimitTime limitTime) {
        this.limitTime = limitTime;
    }

    public OauthApplicationLevel getLevel() {
        return level;
    }

    public void setLevel(OauthApplicationLevel level) {
        this.level = level;
    }

}
