package com.yunhou.openapi.dao.mysql;

import java.util.List;

import com.yunhou.openapi.model.oauth.OauthLimit;

public interface OauthLimitDao {
    public void insert(OauthLimit oauthLimit);

    public List<OauthLimit> selectAll();
}
