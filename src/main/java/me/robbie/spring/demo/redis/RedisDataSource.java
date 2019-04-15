package me.robbie.spring.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-08 18:29
 * @since [产品/模块版本]
 */
public interface RedisDataSource {
     Jedis getRedisClient();
     void returnResource(Jedis shardedJedis);
     void returnResource(Jedis shardedJedis,boolean broken);
}
