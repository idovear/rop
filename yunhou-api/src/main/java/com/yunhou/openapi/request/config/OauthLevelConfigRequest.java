package com.yunhou.openapi.request.config;

import com.rop.AbstractRopRequest;

public class OauthLevelConfigRequest extends AbstractRopRequest {
    private long id;
    private String level;// 等级
    private String desc;// 等级描述
    private String methods;// 可访问的方法列表 多个方法逗号隔开
    private long expireTokenTime;// 秒 token过期时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public long getExpireTokenTime() {
        return expireTokenTime;
    }

    public void setExpireTokenTime(long expireTokenTime) {
        this.expireTokenTime = expireTokenTime;
    }

}
