package com.rop.session;

/**
 * 会话管理器
 * 
 * @author libinsong@gmail.com
 * @author 陈雄华
 */
public interface SessionManager {

    /**
     * 注册一个会话(授权中心 授权成功后添加)
     * 
     * @param session
     */
    void addSession(String sessionId, Session session);

    /**
     * 从注册表中获取会话
     * 
     * @param sessionId
     * @return
     */
    Session getSession(String appkey, String accessToken);

    /**
     * 移除这个会话（授权中心注销后 删除）
     * 
     * @param sessionId
     * @return
     */
    void removeSession(String sessionId);
}
