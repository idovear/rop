package com.yunhou.openapi.dao.dataRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.common.model.TokenInfo;
import com.yunhou.openapi.common.util.SerializeUtil;

@Service
public class TokenData {
    @Autowired
    private RedisClient redisClient;

    /* 访问令牌 */
    public TokenInfo getTokenInfo(String token) {
        byte[] bs = redisClient.get(RedisMark.REDIS_OAUTH_TOKEN, token);
        if(bs==null){
            return null;
        }
        return (TokenInfo) SerializeUtil.unserialize(bs);
    }
}
