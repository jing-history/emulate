package tk.jingzing.jzoffer;

import java.util.LinkedList;

/**
 * @Description:用两个队列实现栈
 *
 * 思路：通过一系列的栈的压入和弹出操作来分析用队列模拟一个栈的过程，如图所示，我们先往栈内压入一个元素a。由于两个队列现在都是空，我们
 * 可以选择把a插入两个队列中的任一个。我们不妨把a插入queue1。接下来继续网栈内压入b,c两个元素。我们把它们都插入queue1。这个时候 queue1包含3个元素a,b,c其中a位于队列的头部，c位于队列的尾部。
 * 现在我们考虑从栈内弹出一个元素。根据栈的后入先出的原则，最后被压入栈的c应该最先被弹出。由于c位于queue1的尾部，而我们每次只能从队列的头部删除元素，因此我们可以从queueu中依次删除a/b/c并插入到queue2中，
 * 再从queue1中删除c。这就相当于从栈中弹出元素c了。我们可以用同样的方法从栈内弹出元素b。
 * 接下来我们考虑从栈内压入一个元素d.此时queue1已经有了一个元素，我们就把d插入到queue1的尾部。如果我们再从栈内弹出一个元素，此时被弹出的应该是最后被压入的d.由于d位于queue1的尾部，我们只能先从头部删除 queue1的元素并插入到queue2，直到queue1中遇到d再直接把它删除
 *
 * Created by Louis Wang on 2016/8/9.
 */
public class E07StacksWithTwoQueue {

    private LinkedList<String> queue1;
    private LinkedList<String> queue2;

    public E07StacksWithTwoQueue() {
        queue1 = new LinkedList<String>();
        queue2 = new LinkedList<String>();
    }

    public String pop(){
        String re = null;
        if(queue1.size() == 0 && queue2.size() == 0){
            return null;
        }
        if(queue2.size() == 0){
            while (queue1.size() > 0){
                re = queue1.removeFirst();
                if(queue1.size() != 0){
                    queue2.addLast(re);
                }
            }
        }else if(queue1.size() == 0){
            while (queue2.size() > 0){
                re = queue2.removeFirst();
                if(queue2.size() != 0){
                    queue1.addLast(re);
                }
            }
        }
        return re;
    }

    public String push(String str){
        if(queue1.size() == 0 && queue2.size() == 0){
            queue1.addLast(str);
        }
        if(queue1.size() != 0){
            queue1.addLast(str);
        }else if(queue2.size() != 0){
            queue2.addLast(str);
        }
        return str;
    }

    public static void main(String[] args) {
        E07StacksWithTwoQueue stack=new E07StacksWithTwoQueue();
        String tmp;
        stack.push("1");
        stack.push("2");
        stack.push("3");
        tmp=stack.pop();
        System.out.println(tmp);//3
        stack.push("4");
        tmp=stack.pop();
        System.out.println(tmp);//4
        tmp=stack.pop();
        System.out.println(tmp);//2
        stack.push("5");
        stack.push("6");
        tmp=stack.pop();
        System.out.println(tmp);//6
        tmp=stack.pop();
        System.out.println(tmp);//5
        tmp=stack.pop();
        System.out.println(tmp);//1
    }
}
