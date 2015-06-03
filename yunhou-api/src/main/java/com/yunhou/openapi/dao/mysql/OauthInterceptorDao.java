package com.yunhou.openapi.dao.mysql;

import java.util.List;

import com.yunhou.openapi.model.oauth.OauthInterceptor;

/**
 * 
 * 应用访问拦截dao<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月2日 下午2:39:43
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthInterceptorDao {

    public List<OauthInterceptor> selectAll();
}
