package com.yunhou.openapi.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisClient {
    private Jedis jedis;// 非切片额客户端连接
    // private JedisPool jedisPool;// 非切片连接池
    // private ShardedJedis shardedJedis;// 切片额客户端连接
    // private ShardedJedisPool shardedJedisPool;// 切片连接池

    public RedisClient() {
        // initialPool();
        // initialShardedPool();
        // shardedJedis = shardedJedisPool.getResource();
        // jedis = jedisPool.getResource();
        jedis = new Jedis("10.201.7.238", 6379);
        jedis.set("aa", "aa");
        String value = jedis.get("aa");
        System.out.println(value);
    }

    // /**
    // * 初始化非切片池
    // */
    // private void initialPool() {
    // // 池基本配置
    // JedisPoolConfig config = new JedisPoolConfig();
    // config.setMaxTotal(20);
    // config.setMaxIdle(5);
    // config.setTestOnBorrow(false);
    //
    // jedisPool = new JedisPool(config, "10.201.7.238", 6379);
    // }

    // /**
    // * 初始化切片池
    // */
    // private void initialShardedPool() {
    // // 池基本配置
    // JedisPoolConfig config = new JedisPoolConfig();
    // config.setMaxTotal(20);
    // config.setMaxIdle(5);
    // config.setTestOnBorrow(false);
    // // slave链接
    // List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
    // shards.add(new JedisShardInfo("10.201.7.238", 6379, "master"));
    //
    // // 构造池
    // shardedJedisPool = new ShardedJedisPool(config, shards);
    // }

    public void show() {
        KeyOperate();
        StringOperate();
        ListOperate();
        SetOperate();
        SortedSetOperate();
        HashOperate();
//        jedisPool.returnResource(jedis);
        // shardedJedisPool.returnResource(shardedJedis);
    }

    private void KeyOperate() {
        System.out.println("======================key==========================");
        // 清空数据
        System.out.println("清空库中所有数据：" + jedis.flushDB());
        // 判断key否存在
        System.out.println("判断key999键是否存在：" + jedis.exists("key999"));
        System.out.println("新增key001,value001键值对：" + jedis.set("key001", "value001"));
        System.out.println("判断key001是否存在：" + jedis.exists("key001"));
        // 输出系统中所有的key
        System.out.println("新增key002,value002键值对：" + jedis.set("key002", "value002"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
        // 删除某个key,若key不存在，则忽略该命令。
        System.out.println("系统中删除key002: " + jedis.del("key002"));
        System.out.println("判断key002是否存在：" + jedis.exists("key002"));
        // 设置 key001的过期时间
        System.out.println("设置 key001的过期时间为5秒:" + jedis.expire("key001", 5));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        // 移除某个key的生存时间
        System.out.println("移除key001的生存时间：" + jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型：" + jedis.type("key001"));
        /*
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         * 2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
    }

    private void StringOperate() {
    }

    private void ListOperate() {
    }

    private void SetOperate() {
    }

    private void SortedSetOperate() {
    }

    private void HashOperate() {
    }
}
