package com.yunhou.openapi.api.impl.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequest;
import com.rop.RopRequestContext;
import com.rop.annotation.ServiceMethodBean;
import com.rop.security.MainErrorType;
import com.rop.security.MainErrors;
import com.yunhou.openapi.api.config.OauthApplicationInterface;
import com.yunhou.openapi.common.util.MD5Util;
import com.yunhou.openapi.dao.dataRedis.AppSecretData;
import com.yunhou.openapi.dao.mysql.OauthApplicationDao;
import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.oauth.OauthApplication;
import com.yunhou.openapi.request.config.OauthApplicationRequest;
import com.yunhou.openapi.response.config.OauthAppResponse;

/**
 * 
 * 应用管理配置<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月4日 上午9:48:04
 * @version 1.0
 * @since JDK 1.7
 */
@ServiceMethodBean(version = "1.0")
public class OauthApplicationImpl implements OauthApplicationInterface {

    @Autowired
    private OauthApplicationDao oauthApplicationDao;
    @Autowired
    private AppSecretData appSData;

    public Object getById(OauthApplicationRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthApplication oauthApplication = oauthApplicationDao.select(app.getId());
        return new OauthAppResponse(oauthApplication);
    }

    public Object get(RopRequest request) {
        List<OauthApplication> selectAll = oauthApplicationDao.selectAll();
        List<OauthAppResponse> result = new ArrayList<OauthAppResponse>();
        if (selectAll != null && selectAll.size() > 0) {
            for (OauthApplication oauthApplication : selectAll) {
                result.add(new OauthAppResponse(oauthApplication));
            }
        }
        return result;
    }

    public Object add(OauthApplicationRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (StringUtils.isBlank(app.getUsername())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "username");
        }
        if (StringUtils.isBlank(app.getIphone())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "iphone");
        }
        if (StringUtils.isBlank(app.getRedirectUri())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "redirectUri");
        }
        if (StringUtils.isBlank(app.getMail())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "mail");
        }
        if (StringUtils.isBlank(app.getCompany())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "company");
        }
        OauthApplication oauthApplication = new OauthApplication(app);
        oauthApplication.setAppKey(MD5Util.GetMD5Code(UUID.randomUUID().toString()));
        oauthApplication.setAppSecret(MD5Util.GetMD5Code(UUID.randomUUID().toString()));
        oauthApplicationDao.insert(oauthApplication);
        appSData.put(oauthApplication);
        return new OauthAppResponse(oauthApplication);
    }

    public Object update(OauthApplicationRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        if (app.getInvokeCount() < -1) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "invokeCount");
        }

        OauthApplication orgApp = oauthApplicationDao.select(app.getId());
        if (orgApp == null) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthApplication oauthApplication = new OauthApplication(app);
        oauthApplicationDao.update(oauthApplication);

        if (StringUtils.isNotBlank(oauthApplication.getLevel())) {
            orgApp.setLevel(oauthApplication.getLevel());
        }
        if (app.getInvokeCount() != 0) {
            orgApp.setInvokeCount(oauthApplication.getInvokeCount());
        }
        appSData.put(orgApp);
        return 1;
    }

    public Object delete(OauthApplicationRequest app) {
        RopRequestContext req = app.getRopRequestContext();
        if (app.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthApplication oauthApplication = oauthApplicationDao.select(app.getId());
        if (oauthApplication != null) {
            OauthApplication delApp = new OauthApplication();
            delApp.setId(app.getId());
            delApp.setRecordStatus(RecordStatus.DELETE);
            oauthApplicationDao.update(delApp);
            appSData.del(oauthApplication);
        }
        return 1;
    }
}
