package com.yunhou.openapi.model.security;

/**
 * 
 * 访问日志<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 上午11:22:07
 * @version 1.0
 * @since JDK 1.7
 */
public class RatelimitLog {

    private String appkey;// 应用id
    private String userId;// 用户id
    private String ip;// 访问ip
    private String method;// 调用方法名

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
