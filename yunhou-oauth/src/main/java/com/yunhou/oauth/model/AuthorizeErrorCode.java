package com.yunhou.oauth.model;

/**
 * 
 * 授权错误码<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月3日 上午10:07:51
 * @version 1.0
 * @since JDK 1.7
 */
public enum AuthorizeErrorCode {

    Error_20001("20001", "传入参数错误"),
    Error_20002("20002", "应用验证失败"),
    Error_20003("20003", "回调地址域名不正确"),
    Error_20004("20004", "授权码错误或者失效");

    private String code;
    private String desc;

    AuthorizeErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    
    
}
