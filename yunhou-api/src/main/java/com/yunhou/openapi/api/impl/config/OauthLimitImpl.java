package com.yunhou.openapi.api.impl.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequest;
import com.rop.RopRequestContext;
import com.rop.annotation.ServiceMethodBean;
import com.rop.security.MainErrorType;
import com.rop.security.MainErrors;
import com.yunhou.openapi.api.config.OauthLimitInterface;
import com.yunhou.openapi.dao.dataRedis.InvokeTimesData;
import com.yunhou.openapi.dao.mysql.OauthLimitDao;
import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.oauth.OauthLimit;
import com.yunhou.openapi.request.config.OauthLimitRequest;

@ServiceMethodBean(version = "1.0")
public class OauthLimitImpl implements OauthLimitInterface {

    @Autowired
    private OauthLimitDao oauthLimitDao;

    @Autowired
    private InvokeTimesData invokeData;

    public Object getById(OauthLimitRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLimit select = oauthLimitDao.select(app.getId());
        return select;
    }

    public Object get(RopRequest request) {
        return oauthLimitDao.selectAll();
    }

    public Object add(OauthLimitRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getResource() == null) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "resource");
        }
        if (StringUtils.isBlank(app.getMethods())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "method");
        }
        if (app.getLimitCount() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "limitCount");
        }
        if (app.getLimitTime() == null) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "limitTime");
        }
        if (StringUtils.isBlank(app.getLevel())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "level");
        }
        OauthLimit limit = new OauthLimit(app);
        oauthLimitDao.insert(limit);
        invokeData.put(limit);
        return limit;
    }

    public Object update(OauthLimitRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLimit orgLimit = oauthLimitDao.select(app.getId());
        if (orgLimit == null) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        invokeData.del(orgLimit);
        OauthLimit newLimit = new OauthLimit(app);
        if (app.getResource() != null) {
            orgLimit.setResource(newLimit.getResource());
        }
        if (StringUtils.isNotBlank(app.getMethods())) {
            orgLimit.setMethod(newLimit.getMethod());
        }
        if (app.getLimitCount() > 0) {
            orgLimit.setLimitCount(newLimit.getLimitCount());
        }
        if (app.getLimitTime() != null) {
            orgLimit.setLimitTime(newLimit.getLimitTime());
        }
        if (StringUtils.isNotBlank(app.getLevel())) {
            orgLimit.setLevel(newLimit.getLevel());
        }
        oauthLimitDao.update(orgLimit);
        invokeData.put(orgLimit);
        return 1;
    }

    public Object delete(OauthLimitRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLimit orgLimit = oauthLimitDao.select(app.getId());
        if (orgLimit == null) {
            return null;
        }
        OauthLimit del = new OauthLimit();
        del.setId(app.getId());
        del.setRecordStatus(RecordStatus.DELETE);
        oauthLimitDao.update(del);
        invokeData.del(orgLimit);
        return 1;
    }

}
