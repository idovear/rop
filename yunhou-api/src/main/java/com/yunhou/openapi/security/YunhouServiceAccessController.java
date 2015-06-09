package com.yunhou.openapi.security;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rop.security.ServiceAccessController;
import com.rop.session.Session;
import com.yunhou.openapi.dao.dataRedis.AppSecretData;
import com.yunhou.openapi.dao.dataRedis.ServiceAccessData;

/**
 * <pre>
 * 功能说明：判断应用访问服务权限
 * </pre>
 * 
 * @author 陈雄华
 * @version 1.0
 */
public class YunhouServiceAccessController implements ServiceAccessController {

    @Autowired
    private ServiceAccessData accessData;
    @Autowired
    private AppSecretData secretData;

    public boolean isAppGranted(String appKey, String method, String version) {
        String level = secretData.getRoleLevel(appKey);
        if (StringUtils.isBlank(level)) {
            return false;
        }
        List<String> services = accessData.getServices(level);
        if (services == null || services.size() == 0) {
            return false;
        } else {
            if (services.contains("*")) {
                return true;
            }
            return services.contains(method);
        }
    }

    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}
