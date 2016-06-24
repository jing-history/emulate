package tk.jingzing.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.jingzing.dubbo.bean.Note;
import tk.jingzing.dubbo.bean.NoteBook;
import tk.jingzing.dubbo.bean.NoteBookGroup;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 根据url 读取内容写入到表里面
 * Lombok  要在idea 里面安装插件
 * Created by Louis Wang on 2016/6/22.
 */

public class StoreToDataBaseByThread {
    private static final Logger logger = LoggerFactory.getLogger(StoreToDataBaseByThread.class);

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
    public List<Note> insertToDatabase() {
        final Random random = new Random();
        noteBookGroup.setNoteBookGroupId(1);
        //http://www.cnblogs.com/dolphin0520/p/3938914.html CopyOnWriteArrayList 使用
        final List<Note> noteList = new CopyOnWriteArrayList<Note>();
        Iterator<String> iter = currentHashMap.keySet().iterator();

        try{
            while (iter.hasNext()) {
                final String title = iter.next();
                final String path = currentHashMap.get(title);
                new Thread(){
                    public void run() {
                        Note note = new Note();
                        NoteBook nb = new NoteBook();
                        String content = getHtmlByPath(path);
//                        Blob clobContent;
                    }
                }.start();
            }
        }catch (Exception e){
            logger.error("爬虫抓取网页内容写入到数据库失败！" + e.getLocalizedMessage());
        }
        return null;
    }

    private String getHtmlByPath(String url) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);// get
            // 解决超时
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");

        } catch (Exception e) {
            logger.error("httpClients读取网页内容失败！" + e.getLocalizedMessage() + ":" + url);
            return "error:httpClients读取网页内容失败：失败地址" + url + "===错误信息" + e.getLocalizedMessage();
        }finally {
            httpGet.releaseConnection();
        }
    }
}
