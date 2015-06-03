package com.yunhou.openapi.dao.dataRedis;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rop.RopRequestContext;
import com.rop.security.MainError;
import com.rop.security.MainErrorType;
import com.rop.security.MainErrors;
import com.rop.security.SessionConstans;
import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.dao.mysql.OauthInterceptorDao;
import com.yunhou.openapi.model.oauth.OauthInterceptor;
import com.yunhou.openapi.model.security.RatelimtResource;

/**
 * 
 * 拦截数据中心<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 下午9:43:30
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class InterceptorData {

    private static String keySplit = "-";

    @Autowired
    private OauthInterceptorDao oauthInterceptorDao;

    @Autowired
    private RedisClient redisClient;

    private static Logger logger = LoggerFactory.getLogger(InterceptorData.class);

    @PostConstruct
    public void init() {
        try {
            List<OauthInterceptor> selectAll = oauthInterceptorDao.selectAll();
            if (selectAll != null && selectAll.size() > 0) {
                for (OauthInterceptor interceptor : selectAll) {
                    redisClient.put(RedisMark.REDIS_INTERCEPTOR, getKey(interceptor.getType(), interceptor.getResource()),
                            1 + "", -1);
                }
            }
            logger.info("初始化授权应用数据成功!");
        } catch (Exception e) {
            logger.error("初始化授权应用数据异常", e);
        }
    }

    public boolean isMatch(RopRequestContext req) {
        if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.IP, req.getIp())) != null) {
            return true;
        } else if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.APP_KEY, req.getAppKey())) != null) {
            return true;
        }
        String userId = req.getSession().getAttribute(SessionConstans.USER_ID) + "";
        if (StringUtils.isNotBlank(userId)) {
            if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.USER_ID, userId)) != null) {
                return true;
            }
        }
        return false;
    }

    public MainError isInterceptor(RopRequestContext req) {
        String userId = "";
        if (req.getSession() != null && req.getSession().getAttribute(SessionConstans.USER_ID) != null) {
            userId = req.getSession().getAttribute(SessionConstans.USER_ID) + "";
        }
        if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.IP, req.getIp())) != null) {
            return MainErrors.getError(MainErrorType.SERVICE_INTERCEPTOR, req.getLocale(), "ip:" + req.getIp());
        } else if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.APP_KEY, req.getAppKey())) != null) {
            return MainErrors.getError(MainErrorType.SERVICE_INTERCEPTOR, req.getLocale(), "应用:" + req.getAppKey());
        }
        if (StringUtils.isNotBlank(userId)) {
            if (redisClient.getToString(RedisMark.REDIS_INTERCEPTOR, getKey(RatelimtResource.USER_ID, userId)) != null) {
                return MainErrors.getError(MainErrorType.SERVICE_INTERCEPTOR, req.getLocale(), "用户:" + userId);
            }
        }
        return null;
    }

    private String getKey(RatelimtResource ratelimtResource, String resource) {
        return ratelimtResource.name() + keySplit + resource;
    }

}
