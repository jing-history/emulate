package tk.jingzing.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import tk.jingzing.dubbo.service.BaseService;
import tk.jingzing.dubbo.service.NoteService;
import tk.jingzing.redis.util.RedisCacheManager;

/**
 * redis测试
 * Created by Louis Wang on 2016/7/4.
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-content.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class JuitTest {

    @Autowired
    RedisCacheManager redisCacheManager;

    @Qualifier("NoteServiceImp")
    @Autowired
    BaseService baseService;

    @Autowired
    NoteService noteService;
}
