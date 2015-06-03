package com.yunhou.openapi.common.model;

import java.io.Serializable;

/**
 * 
 * 应用实体对象<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 上午11:34:13
 * @version 1.0
 * @since JDK 1.7
 */
public class AppSecret implements Serializable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 3633211672252971797L;
    private String appKey;// 应用key
    private String appSecret;// 应用密钥
    private String redirect_uri;// 回调地址
    private String level;// 等级

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

}
