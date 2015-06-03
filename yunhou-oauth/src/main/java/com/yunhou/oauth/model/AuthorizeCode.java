package com.yunhou.oauth.model;

/**
 * 
 * 授权码返回参数<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 下午5:08:22
 * @version 1.0
 * @since JDK 1.7
 */
public class AuthorizeCode {

    private String code;
    private String error;
    private String error_description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

}
