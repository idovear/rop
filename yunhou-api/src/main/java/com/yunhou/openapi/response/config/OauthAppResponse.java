package com.yunhou.openapi.response.config;

import java.io.Serializable;
import java.util.Date;

import com.yunhou.openapi.model.oauth.OauthApplication;

public class OauthAppResponse implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String appKey;// 应用key
    private String level;// 应用级别
    private String redirectUri;// 回调地址
    private long invokeCount;// 可调用次数 为-1的时候 没有限制

    /* 创建人用户信息 */
    private String username;// 联系人名称
    private String iphone;// 手机号码
    private String mail;// 邮箱
    private String company;// 开发者公司名称
    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间

    public OauthAppResponse(OauthApplication oauthApplication) {
        this.id = oauthApplication.getId();
        this.appKey = oauthApplication.getAppKey();
        this.level = oauthApplication.getLevel().name();
        this.redirectUri = oauthApplication.getRedirectUri();
        this.invokeCount = oauthApplication.getInvokeCount();
        this.username = oauthApplication.getUsername();
        this.iphone = oauthApplication.getIphone();
        this.mail = oauthApplication.getMail();
        this.company = oauthApplication.getCompany();
        this.createTime = oauthApplication.getCreateTime();
        this.lastModify = oauthApplication.getLastModify();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

}
