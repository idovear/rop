package com.yunhou.openapi.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rop.security.ServiceAccessController;
import com.rop.security.SessionConstans;
import com.rop.session.Session;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class YunhouServiceAccessController implements ServiceAccessController {

    private static final Map<String, List<String>> aclMap = new HashMap<String, List<String>>();

    static {
        ArrayList<String> serviceMethods = new ArrayList<String>();
        serviceMethods.add("user.logon");
        serviceMethods.add("user.logout");
        serviceMethods.add("user.getSession");
        aclMap.put("00003", serviceMethods);
    }

    public boolean isAppGranted(String appKey, String method, String version) {
        if (aclMap.containsKey(appKey)) {
            List<String> serviceMethods = aclMap.get(appKey);
            return serviceMethods.contains(method);
        } else {
            return true;
        }
    }

    public boolean isUserGranted(Session session, String method, String version) {
        String userId = "";
        if (session != null) {
            if (session.getAttribute(SessionConstans.USER_ID) != null) {
                userId = session.getAttribute(SessionConstans.USER_ID) + "";
            }
        }
        return true;
    }
}
