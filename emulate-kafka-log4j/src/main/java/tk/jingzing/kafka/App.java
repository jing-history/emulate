package tk.jingzing.kafka;

import org.apache.log4j.Logger;

/**
 * http://my.oschina.net/itblog/blog/540918
 * @Description:通过log4j输出日志
 * Created by Louis Wang on 2016/7/26.
 */

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            LOGGER.info("Info [" + i + "]");
            Thread.sleep(1000);
        }
    }
}
