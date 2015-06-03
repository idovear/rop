package com.yunhou.oauth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunhou.oauth.util.RedisClient;
import com.yunhou.openapi.common.model.AppSecret;
import com.yunhou.openapi.common.model.RedisMark;
import com.yunhou.openapi.common.model.User;
import com.yunhou.openapi.common.util.SerializeUtil;

/**
 * 
 * 授权数据交互中心<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月3日 下午5:50:15
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class OauthRedis {

    @Autowired
    public RedisClient redisClient;

    /* 授权码 */
    public void putCode(String appkey, String code, User user) {
        redisClient.put(RedisMark.REDIS_APP_CODE, appkey + "-" + code, SerializeUtil.serialize(user), 2 * 60);
    }

    public User getUserByCode(String appkey, String code) {
        byte[] bs = redisClient.get(RedisMark.REDIS_APP_CODE, appkey + "-" + code);
        if (bs == null) {
            return null;
        }
        return (User) SerializeUtil.unserialize(bs);
    }

    /* 应用 */
    public AppSecret getAppByAppKey(String appKey) {
        byte[] bs = redisClient.get(RedisMark.REDIS_APP, appKey);
        if (bs == null) {
            return null;
        }
        return (AppSecret) SerializeUtil.unserialize(bs);
    }

    /* 访问令牌 */
    public void putToken(String token, int expire, String appkey) {
        redisClient.put(RedisMark.REDIS_OAUTH_TOKEN, token, appkey, expire);
    }
}
