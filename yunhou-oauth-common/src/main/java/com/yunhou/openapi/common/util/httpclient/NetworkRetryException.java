/*
 * Copyright (c) 2010-2014 Eefung Software Co.Ltd. All rights reserved.
 *  版权所有(c)2010-2014湖南蚁坊软件有限公司。保留所有权利。
 */

package com.yunhou.openapi.common.util.httpclient;

/**
 * Created by hh on 14-8-20.
 */
public class NetworkRetryException extends Exception {
    public NetworkRetryException(String message, Throwable e) {
        super(message,e);
    }
}
