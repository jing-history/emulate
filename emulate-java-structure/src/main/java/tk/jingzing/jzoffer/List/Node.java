package tk.jingzing.jzoffer.List;

/**
 * http://www.cnblogs.com/smyhvae/p/4761593.html
 * @Description:结点类
 * Created by Louis Wang on 2016/7/21.
 */

public class Node {

    Object element; //数据域
    Node next;  //指针域

    //头结点的构造方法
    public Node(Node nextval) {
        this.next = nextval;
    }

    //非头结点的构造方法
    public Node(Object obj, Node nextval) {
        this.element = obj;
        this.next = nextval;
    }

    //获得当前结点的指针域
    public Node getNext(){
        return this.next;
    }

    //获得当前结点数据域的值
    public Object getElement(){
        return this.element;
    }

    //设置当前结点的指针域
    public void setNext(Node nextval) {
        this.next = nextval;
    }

    //设置当前结点数据域的值
    public void setElement(Object obj) {
        this.element = obj;
    }

    public String toString() {
        return this.element.toString();
    }
}
