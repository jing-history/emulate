package tk.jingzing.development.thread.ifeve;

/**
 * @Description:Java中的ThreadLocal类可以让你创建的变量只被同一个线程进行读和写操作。因此，尽管有两个线程同时执行一段相同的代码，
 * 而且这段代码又有一个指向同一个ThreadLocal变量的引用，但是这两个线程依然不能看到彼此的ThreadLocal变量域。
 * Created by Louis Wang on 2016/7/27.
 */

public class ThreadLocalDemo {

    /**
     * 初始化ThreadLocal
     * 由于ThreadLocal对象的set()方法设置的值只对当前线程可见，那有什么方法可以为ThreadLocal对象设置的值对所有线程都可见
     * 我们可以通过ThreadLocal子类的实现，并覆写initialValue()方法，就可以为ThreadLocal对象指定一个初始化值
     * 在set()方法调用前，当调用get()方法的时候，所有线程都可以看到同一个初始化值。
     */
    /*private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
        @Override protected String initialValue() {
            return "This is the initial value";
        }
    };*/

    public static class MyRunnable implements Runnable{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
