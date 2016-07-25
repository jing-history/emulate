package tk.jingzing.development.thread.ifeve;

/**
 * @Description:这里是一个小小的例子。首先输出执行main()方法线程名字。这个线程JVM分配的。然后开启10个线程，命名为1~10。每个线程输出自己的名字后就退出。
 * Created by Louis Wang on 2016/7/25.
 */

public class ThreadExample {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            new Thread("" + i){
                public void run(){
                    System.out.println("Thread: " + getName() + "running");
                }
            }.start();
        }
    }
}
