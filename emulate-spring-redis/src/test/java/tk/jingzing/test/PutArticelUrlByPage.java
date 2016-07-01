package tk.jingzing.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Louis Wang on 2016/6/21.
 */

public class PutArticelUrlByPage implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PutArticelUrlByPage.class);
  //  public static final String BEGIN_URL = "http://www.kmao.net/news/c1p1";
    public static final String BEGIN_URL = "http://www.kmao.net";
    // 推酷上面的 科技和数码栏目 pageNumber 0到20
    // http://www.tuicool.com/ah/0/20?lang=0//科技
    // http://www.tuicool.com/ah/101050000/20?lang=0//数码
    /*public static final String BEGIN_KJ_URL = "http://www.tuicool.com/ah/0/pageNumber?lang=0";
    public static final String BEGIN_SM_URL = "http://www.tuicool.com/ah/101050000/pageNumber?lang=0";*/
    public static final String BEGIN_KJ_URL = "http://www.kmao.net/breaking/c4p1";
    public static final String BEGIN_SM_URL = "http://www.kmao.net/technology/c5p1";


    ConcurrentHashMap<String, String> currentHashMap;
    int number;

    public PutArticelUrlByPage(ConcurrentHashMap<String, String> currentHashMap, int i) {
        this.number = i;
        this.currentHashMap = currentHashMap;
    }

    public void run() {
        try {
            // 每一个主页开启两个线程
            praseHtml(BEGIN_KJ_URL.replace("pageNumber", "" + number));
            praseHtml(BEGIN_SM_URL.replace("pageNumber", "" + number));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void praseHtml(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);// get
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity);
        logger.info(html);
        putUrl(html);
    }

    private void putUrl(String html) {
        /*String[] bb = html.split("<div class=\"/media newpost-item/");
        for (int i = 1; i < bb.length; i++) {
            String url = BEGIN_URL + bb[i].split("\"")[0];
            String title = bb[i].split("title=\"")[1].split("\"")[0] + ".html";
            title = title.replaceAll("\\/", "").replaceAll("\\*", "").replaceAll("\\?", "").replaceAll("\\|", "");
            currentHashMap.put(title, url);
        }*/

        currentHashMap.put("标题1", "www.baidu.com");
        currentHashMap.put("标题2", "www.baidu.com");
        currentHashMap.put("标题3", "www.baidu.com");
        currentHashMap.put("标题4", "www.baidu.com");
        currentHashMap.put("标题5", "www.baidu.com");
        currentHashMap.put("标题6", "www.baidu.com");
    }

}
