package com.yunhou.oauth.model;

/**
 * 
 * 访问令牌<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月3日 上午12:27:50
 * @version 1.0
 * @since JDK 1.7
 */
public class Token {

    private String access_token;// 2YotnFZFEjr1zCsicMWpAA
    private long expires_in;// 过期时间 10(10s之后过期)
    private String refresh_token;// 2YotnFZFEjr1zCsicMWpAA
    private long re_expires_in;// refresh_token过期时间
    private String userId;// 云猴网对应账号id
    private String username;//云猴网对应账号 用户名

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getRe_expires_in() {
        return re_expires_in;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRe_expires_in(long re_expires_in) {
        this.re_expires_in = re_expires_in;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
