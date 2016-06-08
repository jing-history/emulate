package tk.jingzing.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序
 * Created by Louis Wang on 2016/6/7.
 */
@Component
@Order(value=2)
public class MyStartupRunner1 implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner1.class);

    @Override
    public void run(String... args) throws Exception {
     //   System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 11111111 <<<<<<<<<<<<<");

        logger.debug("日志输出测试 Debug");
        logger.trace("日志输出测试 Trace");
        logger.info("日志输出测试 Info");
    }
}
