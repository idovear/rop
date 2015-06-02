package com.yunhou.openapi.model.security;

/**
 * 
 * ratelimit来源限制枚举<br/>
 * 默认应用级别权限组控制，当配置应用限制时 权限组不做限制
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 上午11:03:42
 * @version 1.0
 * @since JDK 1.7
 */
public enum RatelimtResource {
    /**
     * ip限制
     */
    IP,
    /**
     * 用户级别限制
     */
    USER_ID,
    /**
     * 应用级别限制
     */
    APP_KEY,
}
