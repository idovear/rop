/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-7-17
 */
package com.yunhou.openapi.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rop.security.SessionConstans;
import com.rop.session.AbstractSession;
import com.rop.session.Session;
import com.rop.session.SessionManager;

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

    private final Map<String, Session> sessionCache = new ConcurrentHashMap<String, Session>(128, 0.75f, 32);

    public void addSession(String sessionId, Session session) {
    }

    public Session getSession(String sessionId) {// 判断userId是否存在
        Session session = new AbstractSession() {
        };
        session.setAttribute("roleLevel", "");
        session.setAttribute("name", "idovear");
        session.setAttribute(SessionConstans.USER_ID, "idovear");

        // session = sessionCache.get(sessionId);
        if (session.getAttribute(SessionConstans.USER_ID) == null) {
            return null;
        }
        return session;
    }

    public void removeSession(String sessionId) {
    }
}
