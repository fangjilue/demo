package me.robbie.spring.demo.test;

import me.robbie.spring.demo.Run;
import me.robbie.spring.demo.redis.RedisDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-04-08 18:35
 * @since [产品/模块版本]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Run.class)
public class RedisTest {

    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisDataSource redisDataSource;

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = jedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public Boolean exists(String key) {
        Boolean result = false;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    @Test
    public void test(){
        System.out.println(set("test.a","123"));
        System.out.println(get("test.a"));
        System.out.println(exists("test.a"));
    }

}
