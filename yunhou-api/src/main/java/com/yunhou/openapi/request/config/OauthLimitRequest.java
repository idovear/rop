package com.yunhou.openapi.request.config;

import com.rop.AbstractRopRequest;
import com.yunhou.openapi.model.oauth.OauthlimitTime;
import com.yunhou.openapi.model.security.RatelimtResource;

public class OauthLimitRequest extends AbstractRopRequest {
    private long id;
    private RatelimtResource resource;// 来源
    private String methods;// 方法
    private int limitCount;// 限制次数
    private OauthlimitTime limitTime;// 限制次数 时间单位
    private String level;// 应用等级

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

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
