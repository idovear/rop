package com.yunhou.openapi.model.oauth;

import java.util.Date;

import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.request.config.OauthApplicationRequest;

/**
 * 
 * 授权应用实体类<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 下午2:39:50
 * @version 1.0
 * @since JDK 1.7
 */
public class OauthApplication {

    private long id;
    private String appKey;// 应用key
    private String appSecret;// 应用密钥
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
    private RecordStatus recordStatus;// 字段状态

    public OauthApplication() {

    }

    public OauthApplication(OauthApplicationRequest req) {
        this.id = req.getId();
        this.level = req.getLevel();
        this.redirectUri = req.getRedirectUri();
        this.invokeCount = req.getInvokeCount();
        this.username = req.getUsername();
        this.iphone = req.getIphone();
        this.mail = req.getMail();
        this.company = req.getCompany();
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

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
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

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

}
