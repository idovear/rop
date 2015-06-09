package com.yunhou.openapi.api.impl.config;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.RopRequest;
import com.rop.RopRequestContext;
import com.rop.annotation.ServiceMethodBean;
import com.rop.security.MainErrorType;
import com.rop.security.MainErrors;
import com.yunhou.openapi.api.config.OauthLevelConfigInterface;
import com.yunhou.openapi.dao.dataRedis.ServiceAccessData;
import com.yunhou.openapi.dao.mysql.OauthLevelConfigDao;
import com.yunhou.openapi.model.RecordStatus;
import com.yunhou.openapi.model.oauth.OauthLevelConfig;
import com.yunhou.openapi.request.config.OauthLevelConfigRequest;

@ServiceMethodBean(version = "1.0")
public class OauthLevelConfigImpl implements OauthLevelConfigInterface {
    @Autowired
    private OauthLevelConfigDao configDao;
    @Autowired
    private ServiceAccessData accessData;

    public Object getLevels(RopRequest request) {
        return configDao.getLevels();
    }

    public Object getById(OauthLevelConfigRequest config) {
        RopRequestContext req = config.getRopRequestContext();
        if (config.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLevelConfig level = configDao.select(config.getId());
        return level;
    }

    public Object get(RopRequest request) {
        return configDao.selectAll();
    }

    public Object add(OauthLevelConfigRequest config) {
        RopRequestContext req = config.getRopRequestContext();
        if (StringUtils.isBlank(config.getLevel())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "level");
        }
        if (StringUtils.isBlank(config.getDesc())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "desc");
        }
        if (StringUtils.isBlank(config.getMethods())) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "methods");
        }
        if (config.getExpireTokenTime() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "expireTokenTime");
        }
        OauthLevelConfig level = new OauthLevelConfig(config);
        configDao.insert(level);
        accessData.put(level.getLevel(), level.getServiceMethods());
        return level;
    }

    public Object update(OauthLevelConfigRequest config) {
        RopRequestContext req = config.getRopRequestContext();
        if (config.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLevelConfig orgLevel = configDao.select(config.getId());
        OauthLevelConfig level = new OauthLevelConfig(config);
        configDao.update(level);
        List<String> methods = orgLevel.getServiceMethods();
        if (level.getServiceMethods() != null && level.getServiceMethods().size() > 0) {
            methods = level.getServiceMethods();
        }
        accessData.put(orgLevel.getLevel(), methods);
        return 1;
    }

    public Object delete(OauthLevelConfigRequest config) {
        RopRequestContext req = config.getRopRequestContext();
        if (config.getId() <= 0) {
            return MainErrors.getError(MainErrorType.INVALID_ARGUMENTS, req.getLocale(), req.getMethod(), "id");
        }
        OauthLevelConfig orgConfig = configDao.select(config.getId());
        if (orgConfig == null) {
            return null;
        }
        OauthLevelConfig del = new OauthLevelConfig();
        del.setId(config.getId());
        del.setRecordStatus(RecordStatus.DELETE);
        configDao.update(del);
        accessData.del(orgConfig.getLevel());
        return 1;
    }

}
