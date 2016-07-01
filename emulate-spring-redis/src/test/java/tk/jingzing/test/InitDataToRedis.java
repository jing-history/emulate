package tk.jingzing.test;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import tk.jingzing.redis.annation.RedisCache;
import tk.jingzing.redis.dao.RedisDao;
import tk.jingzing.redis.util.RedisCacheManager;
import tk.jingzing.redis.util.RedisCachePool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 初始化数据库数据到redis
 * Created by Louis Wang on 2016/6/24.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-content.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class InitDataToRedis {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    RedisCacheManager redisCacheManager;

    @Test
    @Transactional
    public void init() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException {
        Jedis jedis = null;
        RedisCachePool pool = null;
        try{
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            Transaction tx = jedis.multi();
            tx.flushDB();// 清空所有数据
            ResourcePatternResolver rp = new PathMatchingResourcePatternResolver();
            Resource[] resources = rp.getResources("classpath:tk/jingzing/dubbo/**/*.class");
            for (Resource resource : resources) {
                String className = resource.getFile().getPath().split("classes\\\\")[1].replaceAll("\\\\", ".").replaceAll(".class", "");
                Class<?> clzz = Thread.currentThread().getContextClassLoader().loadClass(className);
                if(clzz.getAnnotation(RedisCache.class) != null){
                    List<Object> listObject = getData(clzz);
                    RedisDao rd = new RedisDao(tx);
                    rd.insertListToredis(listObject);
                }
            }
            tx.exec();  // 提交事物
        }catch (Exception e){
            pool.returnResource(jedis);
            e.printStackTrace();
            log.error("" + e.getLocalizedMessage());
        }
    }

    private List<Object> getData(Class<?> clzz) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field field = clzz.getDeclaredField("className");
        field.setAccessible(true);
        String tableName = field.get(clzz).toString();
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from " + tableName);
        List<Object> objectList = query.list();
        if (objectList.size() > 3) {
            objectList = objectList.subList(0, 1);
        }

        if (!tableName.equals("Note")) {
            objectList = new ArrayList<Object>();
        }
        return objectList;
    }
}
