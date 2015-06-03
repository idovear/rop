package com.yunhou.oauth.model;

/**
 * 
 * 授权用户登录实体<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 下午3:48:52
 * @version 1.0
 * @since JDK 1.7
 */
public class Authorize {

    private String app_key;
    private String response_type;// 默认code
    private String redirect_uri;// 回调地址
    private String state;// 维持应用的状态
    private String view;// 默认为web

    /* 获取访问令牌参数 */
    private String app_secret;
    private String grant_type;// 默认 authorization_code
    private String code;//

    /* 登录 */
    private String username;
    private String password;
    private String sessionId;// 云猴网用户登录sessionId
    private boolean isAuthorize;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorize() {
        return isAuthorize;
    }

    public void setAuthorize(boolean isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

}
