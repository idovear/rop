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
    public static final String REDIS_APP = "app";// 授权应用信息

    public static final String REDIS_APP_CODE = "app_code";// 授权码 过期时间2分钟

    public static final String REDIS_OAUTH_TOKEN = "oauth_token";// 访问令牌

    public static final String REDIS_OAUTH_REFRESH_TOKEN = "oauth_ref_token";// 刷新访问令牌

    /* 安全 */
    public static final String REDIS_INTERCEPTOR = "interceptor";// 拦截信息

    /* 频率 */
    public static final String REDIS_INVOKE_FRE = "invoke_fre";// 调用频率限制

    public static final String REDIS_INVOKE_FRE_UNIT = "invoke_fre_unit";// 调用频率单位

    public static final String REDIS_INVOKE_FRE_COUNT = "invoke_fre_count";// 调用频率统计

    /* 次数 */
    public static final String REDIS_INVOKE_LIMIT = "invoke_limit";// 调用次数限制

    public static final String REDIS_INVOKE_LIMIT_COUNT = "invoke_limit_count";// 调用次数限制

}
