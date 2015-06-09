package com.yunhou.openapi.dao.mysql;

import java.util.List;

import com.yunhou.openapi.model.oauth.OauthLevelConfig;

/**
 * 
 * 应用等级管理<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月8日 上午11:37:56
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthLevelConfigDao {

    public void insert(OauthLevelConfig config);

    public OauthLevelConfig select(long id);

    public List<OauthLevelConfig> selectAll();

    public void update(OauthLevelConfig config);

    public List<String> getLevels();
}
