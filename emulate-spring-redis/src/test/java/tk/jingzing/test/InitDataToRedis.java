package tk.jingzing.test;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import tk.jingzing.redis.redis.RedisCacheManager;

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


}
