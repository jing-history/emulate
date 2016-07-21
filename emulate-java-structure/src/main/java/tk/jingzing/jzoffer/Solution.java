package tk.jingzing.jzoffer;

/**
 * @Description: 问题描述：定义一个函数，输入一个链表的头结点，反转该链表并输出翻转后链表的头结点；
 * 为避免反转时，当前节点的next指针指向前驱lastNode，而无法继续索引其后继nextNode，故在遍历过程中，注意要同时记录其前驱与后继；
 * 同时注意鲁棒性：如输入链表头指针为null或者只有一个节点的情况；
 * Created by Louis Wang on 2016/7/21.
 */

public class Solution {

        public ListNode ReverseList(ListNode head) {
        //为避免发生断裂，要注意记录一个Node的前驱及后继
        ListNode lastNode = null;
        ListNode nextNode = null;
        ListNode revHead  = null;//翻转后的头节点

        while(head != null)
        {
            nextNode = head.next;
            head.next = lastNode;

            //确定翻转后的首节点
            if(nextNode == null)
                revHead = head;

            lastNode = head;
            head = nextNode;
        }
        return revHead;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
