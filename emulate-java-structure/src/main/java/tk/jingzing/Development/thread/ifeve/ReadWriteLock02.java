package tk.jingzing.development.thread.ifeve;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:读锁重入
 * 要保证某个线程中的读锁可重入，要么满足获取读锁的条件（没有写或写请求），要么已经持有读锁（不管是否有写请求）。
 * Created by Louis Wang on 2016/8/9.
 */

public class ReadWriteLock02 {
    private Map<Thread,Integer> readingThreads = new HashMap<Thread, Integer>();

    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead()throws InterruptedException{
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)){
            wait();
        }
        readingThreads.put(callingThread,(getAccessCount(callingThread) + 1));
    }

    public synchronized void unlockRead(){
        Thread callingThread = Thread.currentThread();
        int accessCount = getAccessCount(callingThread);
        if(accessCount == 1){
            readingThreads.remove(callingThread);
        }else {
            readingThreads.put(callingThread, (accessCount -1));
        }
        notifyAll();
    }

    private int getAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if(accessCount == null) return 0;
        return accessCount.intValue();
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if(writers > 0) return false;
        if(isReader(callingThread)) return true;
        if(writeRequests > 0) return false;
        return true;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }
}
