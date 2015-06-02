package com.yunhou.openapi.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {
        // RedisClient redisClient = new RedisClient();
        // redisClient.show();

        Jedis jedis = new Jedis("localhost", 6379);
        String value = jedis.get("aa");
        System.out.println(value);
    }
}
