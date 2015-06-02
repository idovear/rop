package com.yunhou.openapi.model.oauth;

import java.util.Date;

import com.yunhou.openapi.model.RecordStatus;

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
    private OauthApplicationLevel level;// 应用级别

    /* 创建人用户信息 */
    private String username;// 联系人名称
    private String iphone;// 手机号码
    private String mail;// 邮箱
    private String company;// 开发者公司名称

    private Date createTime = new Date();// 创建时间
    private Date lastModify = new Date();// 最后修改时间
    private RecordStatus recordStatus;// 字段状态

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

    public OauthApplicationLevel getLevel() {
        return level;
    }

    public void setLevel(OauthApplicationLevel level) {
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
