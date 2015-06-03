package com.yunhou.oauth.util;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisClient {
    private static JedisPool jedisPool;

    @Value("${redis.id}")
    private String redisIp;

    @Value("${redis.port}")
    private int redisPort;

    @PostConstruct
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, redisIp, redisPort);
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void put(String redisId, String key, byte[] obj, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set((redisId + ":" + key).getBytes(), obj);
            if (expire > 0) {
                jedis.expire(redisId + ":" + key, expire);
            }
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public byte[] get(String redisId, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get((redisId + ":" + key).getBytes());
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public void put(String redisId, String key, String obj, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(redisId + ":" + key, obj);
            if (expire > 0) {
                jedis.expire(redisId + ":" + key, expire);
            }
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String getToString(String redisId, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(redisId + ":" + key);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public void incr(String redisId, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.incr(redisId + ":" + key);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public long count(String redisId, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            Set<String> keys = jedis.keys(redisId + ":" + key);
            if (keys == null) {
                return 0;
            }
            return keys.size();
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
    }

}
