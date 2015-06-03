package com.yunhou.oauth.model;

public class AuthorizeError {

    private String code;
    private String desc;

    public AuthorizeError() {

    }

    public AuthorizeError(AuthorizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.desc = errorCode.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
