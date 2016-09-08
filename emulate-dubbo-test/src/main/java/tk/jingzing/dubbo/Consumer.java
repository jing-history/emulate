package tk.jingzing.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:消费者
 * Created by Louis Wang on 2016/9/8.
 */

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String res = demoService.sayHello("world"); // 执行远程方法
        System.out.println( res ); // 显示调用结果
    }
}
