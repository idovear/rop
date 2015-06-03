package com.yunhou.openapi.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

    private static JedisPool jedisPool;

    public static void main(String[] args) {

        // Jedis jedis = new Jedis("localhost", 6379);
        // String value = jedis.get("aa");
        // System.out.println(value);
        initialPool();

        Jedis jedis = jedisPool.getResource();
        long count = jedis.keys("app:*").size();
        jedis.incr("testCount");
        System.out.println("");
    }

    private static void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }
}
