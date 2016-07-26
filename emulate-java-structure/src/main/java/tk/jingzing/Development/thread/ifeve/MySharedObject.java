package tk.jingzing.development.thread.ifeve;

/**
 * @Description:调用栈和本地变量存放在线程栈上，对象存放在堆上。
 * 一个本地变量可能是原始类型，在这种情况下，它总是“呆在”线程栈上。
 * 一个本地变量也可能是指向一个对象的一个引用。在这种情况下，引用（这个本地变量）存放在线程栈上，但是对象本身存放在堆上
 * 一个对象可能包含方法，这些方法可能包含本地变量。这些本地变量任然存放在线程栈上，即使这些方法所属的对象存放在堆上。
 * 一个对象的成员变量可能随着这个对象自身存放在堆上。不管这个成员变量是原始类型还是引用类型。
 * 静态成员变量跟随着类定义一起也存放在堆上。
 * Created by Louis Wang on 2016/7/26.
 */

public class MySharedObject {

    //static variable pointing to instance of MySharedObject
    public static final MySharedObject sharedInstance = new MySharedObject();

    //member variables pointing to two objects on the heap
    public Integer object2 = new Integer(22);
    public Integer object4 = new Integer(44);

    public long member1 = 12345;
    public long member2 = 67890;


}

