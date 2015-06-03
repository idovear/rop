package com.yunhou.openapi.common.model;

import java.io.Serializable;

public class User implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     */  
    private static final long serialVersionUID = 1L;
    
    private String username;//云猴网账号名
    private String userId;//云猴网账号id
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    

}
