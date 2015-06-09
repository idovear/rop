package com.yunhou.openapi.api.impl.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequest;
import com.rop.RopRequestContext;
import com.rop.annotation.ServiceMethodBean;
import com.rop.security.MainErrorType;
import com.rop.security.MainErrors;
import com.yunhou.openapi.api.config.OauthInterceptorInterface;
import com.yunhou.openapi.dao.dataRedis.InterceptorData;
import com.yunhou.openapi.dao.mysql.OauthInterceptorDao;
import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.oauth.OauthInterceptor;
import com.yunhou.openapi.request.config.OauthInterceptorRequest;

@ServiceMethodBean(version = "1.0")
public class OauthInterceptorImpl implements OauthInterceptorInterface {

    @Autowired
    private OauthInterceptorDao oauthInterceptorDao;
    @Autowired
    private InterceptorData interData;

    public Object getById(OauthInterceptorRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthInterceptor oauthInterceptor = oauthInterceptorDao.select(app.getId());
        return oauthInterceptor;
    }

    public Object get(RopRequest request) {
        return oauthInterceptorDao.selectAll();
    }

    public Object add(OauthInterceptorRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (StringUtils.isBlank(app.getResource())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "resource");
        }
        if (app.getType() == null) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "type");
        }
        OauthInterceptor oauthInterceptor = new OauthInterceptor(app);
        oauthInterceptorDao.insert(oauthInterceptor);
        interData.put(oauthInterceptor);
        return oauthInterceptor;
    }

    public Object update(OauthInterceptorRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        if (StringUtils.isBlank(app.getResource()) && app.getType() == null) {
            return 1;
        }
        OauthInterceptor orgInter = oauthInterceptorDao.select(app.getId());
        OauthInterceptor newInter = new OauthInterceptor(app);
        oauthInterceptorDao.update(newInter);
        if (StringUtils.isBlank(app.getResource())) {
            newInter.setResource(orgInter.getResource());
        }
        if (app.getType() == null) {
            newInter.setType(orgInter.getType());
        }
        interData.put(newInter);
        interData.del(orgInter);
        return 1;
    }

    public Object delete(OauthInterceptorRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthInterceptor orgInter = oauthInterceptorDao.select(app.getId());
        if (orgInter != null) {
            OauthInterceptor del = new OauthInterceptor();
            del.setId(app.getId());
            del.setRecordStatus(RecordStatus.DELETE);
            oauthInterceptorDao.update(del);
            interData.del(orgInter);
        }
        return 1;
    }

}
