package tk.jingzing.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.hibernate.Session;
import tk.jingzing.dubbo.bean.NoteBookGroup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 根据url 读取内容写入到表里面
 * Created by Louis Wang on 2016/6/22.
 */

public class StoreToDataBaseByThread {
    private ConcurrentHashMap<String, String> currentHashMap;
    private Session session;
    private CountDownLatch latch;

    // 默认笔记本组id是1
    private NoteBookGroup noteBookGroup = new NoteBookGroup();

    // 多线程httpclient解决超时连接
    private static RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(500000).setConnectTimeout(500000).build();

    // http请求连接池
    private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    // 一个服务器只有一个客户端
    private CloseableHttpClient httpClients = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();

    public StoreToDataBaseByThread(ConcurrentHashMap<String, String> currentHashMap, Session session, CountDownLatch latch) {
        this.currentHashMap = currentHashMap;
        this.session = session;
        this.latch = latch;
    }

    /**
     * @Description:
     * @see:多单线程插入==》map大小： 插入数据库耗时：50103ms
     * @return:void
     */
    /*public List<Note> insertToDatabase() {

    }*/
}
