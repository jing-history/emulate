package tk.jingzing.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:服务提供者
 * Created by Louis Wang on 2016/9/8.
 */

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();
        System.out.println("请按任意键退出");
        System.in.read(); // 按任意键退出
    }
}
