package tk.jingzing.redis.synchronize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import tk.jingzing.redis.dao.RedisDao;
import tk.jingzing.redis.util.RedisCacheManager;
import tk.jingzing.redis.util.RedisCachePool;
import tk.jingzing.redis.util.RedisDataBaseType;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:NotifyDataBase.java
 * @Description: pub/sub 异步调用类
 * Created by Louis Wang on 2016/7/4.
 */
@Slf4j
@Service("notifyDataBase")
public class NotifyDataBase extends JedisPubSub{

    @Autowired
    RedisUpdateToDataBase redisUpdateToDataBase;

    @Autowired
    RedisCacheManager redisCacheManager;

    public void onMessage(String channel, String sql) {
        log.info("redis更新转换成数据库==》sql:" + sql);

        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        final Jedis jedis = pool.getResource();

        Long length = jedis.llen(RedisDao.LOG);
        int n = 2;  // 如果log的list size 达到n 的时候就一次性执行更新。测试的时候就弄成2
        List<String> list = new ArrayList<String>();
        if(length >= n){
            for (int i = 0; i < n; i++) {
                String sqlStr = jedis.lpop(RedisDao.LOG);   // 删除list首元素
                list.add(sqlStr);
            }

            // 是否执行成功
            boolean flag = redisUpdateToDataBase.excuteUpdate(list);
            if (!flag) {
                for (String oldSql : list) {
                    jedis.lpush(RedisDao.LOG, oldSql);// 更新失败重新添加到list里面
                }
            }
        }
    }

    public void onPMessage(String s, String s1, String s2) {
        log.info("onPMessage");
    }

    public void onSubscribe(String s, int i) {
        log.info("开始监控redis变化！");
    }

    public void onUnsubscribe(String s, int i) {
        log.info("onUnsubscribe");
    }

    public void onPUnsubscribe(String s, int i) {
        log.info("onPUnsubscribe");
    }

    public void onPSubscribe(String s, int i) {
        log.info("onPSubscribe");
    }
}
