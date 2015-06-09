package com.yunhou.openapi.common.model;

/**
 * 
 * redis数据存储标识 相当于表名<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月3日 下午2:49:09
 * @version 1.0
 * @since JDK 1.7
 */
public interface RedisMark {

    /* 应用授权 */
    public static final String REDIS_APP = "app:config";// 授权应用信息

    public static final String REDIS_APP_CODE = "app:code";// 授权码 过期时间2分钟

    public static final String REDIS_OAUTH_TOKEN = "app:token";// 访问令牌

    public static final String REDIS_OAUTH_REFRESH_TOKEN = "oauth:reftoken";// 刷新访问令牌

    /* 安全 */
    public static final String REDIS_INTERCEPTOR = "interceptor";// 拦截信息

    /* 频率 */
    public static final String REDIS_INVOKE_FRE = "invoke:fre:config";// 调用频率限制

    public static final String REDIS_INVOKE_FRE_UNIT = "invoke:fre:unit";// 调用频率单位

    public static final String REDIS_INVOKE_FRE_COUNT = "invoke:fre:count";// 调用频率统计

    /* 次数 */
    public static final String REDIS_INVOKE_LIMIT = "invoke:limit.config";// 调用次数限制

    public static final String REDIS_INVOKE_LIMIT_COUNT = "invoke:limit:count";// 调用次数限制

    /* 决定用户所有 */
    public static final String REDIS_SERVICE_ACCESS = "service_access";// 根据等级判断应用是否调用某方法权限
}
