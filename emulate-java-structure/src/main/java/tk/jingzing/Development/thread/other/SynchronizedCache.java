package tk.jingzing.development.thread.other;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * http://www.importnew.com/20865.html
 * @Description:白板编程，实现一个缓存系统 “synchronized”和“读写锁”
 *
 * 用第一种方法处理，整个过程比较粗线条，代码比较简单单执行效率很低。这种方法的中心思想是不管你是什么操作，但凡涉及到公共资源就都给你同步。
 * 这么做可以是可以但是并不好。第二种用读写锁处理显然是对前者的一个优化，对第二种方法做如下几点说明：关于unlock操作，我们知道只要是上了锁就必须要解锁，
 * 但是有这么一种情况就是当你上完锁后在执行解锁操作前程序出现异常，那这个所可能就一直存在。所以针对这个问题我们一般将unlock操作放在finally代码块中，
 * 就可以保证上了的锁一定会被解。上面的两次if判断，第一个if相信大家很好理解。但为什么要用第二个if呢？再假设一个场景，现在有十个线程来读这个数据，
 * 而这个数据又不存在与缓存区，那么这十个线程中最先到的线程将执行“rw.writeLock().lock();”而另外九个线程将被阻塞，当第一个线程读完以后缓存区实际上已经就有了这个数据，
 * 但另外九个阻塞在“rw.writeLock().lock();”如果不加这层if他们会继续访问数据库，由此可见加了这层if对整个过程影响很大。
 * Created by Louis Wang on 2016/7/29.
 */

public class SynchronizedCache {

    private static Map<String,Object> map = new HashMap<String, Object>();
    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    //首先用synchronized实现
    public synchronized Object getData(String key){
        Object result = map.get(key);
        if(result == null){
            result = "new";//用这步代替访问数据库得数据
        }
        return result;
    }

    //用读写锁实现
    public Object getData2(String key){
        rw.readLock().lock();//在读前先上读锁
        Object result = null;
        try{
            result = map.get(key);
            //这个if比较关键，它避免了多余的几次对数据哭的读取
            if(result == null){
                //如果内存中没有所要数据
                rw.readLock().unlock();
                rw.writeLock().lock();
                if(result == null){
                    try {
                        //我们用这个代替对数据库访问得到数据的步骤
                        result = "new";
                    }finally {
                        rw.writeLock().unlock();
                    }
                    rw.readLock().unlock();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rw.readLock().unlock();
        }
        return result;
    }
}
