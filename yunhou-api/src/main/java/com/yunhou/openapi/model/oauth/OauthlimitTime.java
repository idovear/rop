package com.yunhou.openapi.model.oauth;

/**
 * 
 * 授权时间类型<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 下午2:24:56
 * @version 1.0
 * @since JDK 1.7
 */
public enum OauthlimitTime {

    /**
     * 秒
     */
    SECONDS(1),
    /**
     * 分
     */
    MINUTE(60),
    /**
     * 小时
     */
    HOUR(60 * 60),
    /**
     * 天
     */
    DAY(24 * 60 * 60);

    private int sec;

    OauthlimitTime(int sec) {
        this.sec = sec;
    }

    public int getSec() {
        return sec;
    }

}
