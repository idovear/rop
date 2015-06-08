package com.yunhou.openapi.api.impl.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequest;
import com.yunhou.openapi.api.config.OauthLimitInterface;
import com.yunhou.openapi.dao.dataRedis.InvokeTimesData;
import com.yunhou.openapi.dao.mysql.OauthLimitDao;
import com.yunhou.openapi.request.config.OauthLimitRequest;

public class OauthLimitImpl implements OauthLimitInterface {

    @Autowired
    private OauthLimitDao oauthLimitDao;

    @Autowired
    private InvokeTimesData invokeData;

    public Object getById(OauthLimitRequest app) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object get(RopRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object add(OauthLimitRequest app) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object update(OauthLimitRequest app) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object delete(OauthLimitRequest app) {
        // TODO Auto-generated method stub
        return null;
    }

}
