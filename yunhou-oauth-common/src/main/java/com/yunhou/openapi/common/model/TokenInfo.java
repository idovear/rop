package com.yunhou.openapi.common.model;

import java.io.Serializable;

/**
 * 
 * 存储<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月3日 下午10:49:59
 * @version 1.0
 * @since JDK 1.7
 */
public class TokenInfo implements Serializable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;

    private String appKey;
    private String userId;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
