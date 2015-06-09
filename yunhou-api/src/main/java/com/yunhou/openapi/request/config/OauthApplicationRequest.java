package com.yunhou.openapi.request.config;

import com.rop.AbstractRopRequest;

public class OauthApplicationRequest extends AbstractRopRequest {

    private long id;
    private String level;// 应用级别
    private String redirectUri;// 回调地址
    private long invokeCount = -1;// 可调用次数 为-1的时候 没有限制
    /* 创建人用户信息 */
    private String username;// 联系人名称
    private String iphone;// 手机号码
    private String mail;// 邮箱
    private String company;// 开发者公司名称

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

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
