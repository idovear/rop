/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-7-17
 */
package com.yunhou.openapi.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.security.SessionConstans;
import com.rop.session.AbstractSession;
import com.rop.session.Session;
import com.rop.session.SessionManager;
import com.yunhou.openapi.common.model.TokenInfo;
import com.yunhou.openapi.dao.dataRedis.TokenData;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class YunhouSessionManager implements SessionManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TokenData tokenData;

    public void addSession(String sessionId, Session session) {
    }

    public Session getSession(String appkey, String accessToken) {// 判断userId是否存在
        TokenInfo tokenInfo = tokenData.getTokenInfo(accessToken);
        if (tokenInfo == null || !tokenInfo.getAppKey().equals(appkey)) {
            return null;
        }
        if (StringUtils.isBlank(tokenInfo.getUserId())) {
            return null;
        }
        Session session = new AbstractSession() {
        };
        session.setAttribute(SessionConstans.USER_ID, tokenInfo.getUserId());
        return session;
    }

    public void removeSession(String sessionId) {
    }
}
