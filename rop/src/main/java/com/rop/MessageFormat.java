/**
 * 日期：12-2-10
 */
package com.rop;

import org.springframework.util.StringUtils;

/**
 * 支持的响应的格式类型
 */
public enum MessageFormat {

    json, stream;

    public static MessageFormat getFormat(String value) {
        if (!StringUtils.hasText(value)) {
            return json;
        } else {
            try {
                return MessageFormat.valueOf(value.toLowerCase());
            } catch (IllegalArgumentException e) {
                return json;
            }
        }
    }

    public static boolean isValidFormat(String value) {
        if (!StringUtils.hasText(value)) {
            return true;
        } else {
            try {
                MessageFormat.valueOf(value.toLowerCase());
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

}
