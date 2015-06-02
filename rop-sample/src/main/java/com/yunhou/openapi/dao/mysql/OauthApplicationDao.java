package com.yunhou.openapi.dao.mysql;

import java.util.List;

import com.yunhou.openapi.model.oauth.OauthApplication;

/**
 * 
 * 授权应用dao<br/>
 *
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月1日 下午2:47:28
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthApplicationDao {

    public void insert(OauthApplication oauthApplication);
    
    public List<OauthApplication> selectAll();
}
