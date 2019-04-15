package me.robbie.spring.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-08 18:30
 * @since [产品/模块版本]
 */
//@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

    private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    public Jedis getRedisClient() {
        try {
            Jedis shardJedis = jedisSentinelPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

    public void returnResource(Jedis shardedJedis) {
//        shardedJedisPool.returnResource(shardedJedis);
        shardedJedis.close();
    }

    public void returnResource(Jedis shardedJedis, boolean broken) {
        if (broken) {
//            shardedJedisPool.returnBrokenResource(shardedJedis);
            shardedJedis.close();
        } else {
//            shardedJedisPool.returnResource(shardedJedis);
            shardedJedis.close();
        }
    }

}
