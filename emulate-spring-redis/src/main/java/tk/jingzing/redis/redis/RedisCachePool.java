package tk.jingzing.redis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Louis Wang on 2016/7/1.
 */

public class RedisCachePool {

    private JedisPool jedisPool = null;
    private Integer db;

    public RedisCachePool(Integer db, JedisPool jedisPool) {
        this.db = db;
        this.jedisPool = jedisPool;
    }

    // 选择库表
    Jedis jedisInstance = null;
    public Jedis getResource() {
        if (jedisPool != null) {
            jedisInstance = jedisPool.getResource();
            if (db > 0) {// 每次获得连接之前，切换到对应的数据库。默认的是0
                jedisInstance.select(db);
            }
        }
        return jedisInstance;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
