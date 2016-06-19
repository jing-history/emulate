package tk.jingzing.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName:InitDataToDataBase.java
 * @Description: 初始化网站数据到数据库
 * Created by wangyunjing on 16/6/18.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-content.xml"})
public class InitDataToDataBase extends AbstractTransactionalJUnit4SpringContextTests {
}
