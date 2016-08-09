package tk.jingzing.jzoffer;

import java.util.Stack;

/**
 * http://blog.csdn.net/jsqfengbao/article/details/47089355
 * @Description:what 用两个栈实现一个队列，完成两个函数appendTail和deletedHead,分别是在队列尾部插入节点
 * 和在队列头部删除节点的功能
 *
 * 思路：通过一个具体的例子来分析该队列插入和删除元素的过程。首先插入一个元素a，不妨先把它插入到stack1，此时stack1 中的元素有｛a}，
 * stack2为空。再压入两个元素b和c,还是插入到stack1中，此时stack1中的元素有｛a,b,c}，其中c位于栈顶，而stack2仍然为空。
 * 这个时候，我们试着删除从队列中删除一个元素。按照队列先入先出的规则，由于a比b、c先插入到队列中，最先被删除的元素应该是a。
 * 元素a存储在stack1中，但并不在占顶上，因此不能直接被删除。注意到stack2我们还一直没有使用过，现在是让stack2发挥作用的时候了。
 * 如果我们把stack1中的元素逐个弹出并压入stack2，元素在stack2中的顺序正好和原来的stack1中的顺序相反。
 * 因此经过3次弹出stack1和压入stack2操作之后，stack1为空，而stack2中的元素是｛c,b,a}，这个时候就可以弹出stack2的栈顶a了。此时的stack1为空，而stack2的元素为{c,b}，
 *
 * Created by Louis Wang on 2016/8/9.
 */

public class E07QueueWithTwoStacks {

    private Stack<String> stack1 = new Stack<String>();
    private Stack<String> stack2 = new Stack<String>();

    public void appendTail(String s){
        stack1.push(s);
    }

    public String deletedHead()throws Exception{
        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()){
            throw new Exception("队列为空，不能删除");
        }
        return stack2.pop();
    }

    public static void main(String[] args) throws Exception {
        E07QueueWithTwoStacks test = new E07QueueWithTwoStacks();
        test.appendTail("1");
        test.appendTail("2");
        test.deletedHead();
    }
}
