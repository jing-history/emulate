package tk.jingzing.jzoffer.List;

/**
 * @Description:使用单链表建立一个线性表，依次输入十个0-99之间的随机数，删除第5个元素，打印输出该线性表
 * Created by Louis Wang on 2016/7/22.
 */

public class Test {

    public static final int INT = 7;

    public static void main(String[] args) throws Exception {
        LinkList list = new LinkList();
        for (int i = 0; i < 10; i++) {
            int temp = ((int) (Math.random() * 100)) % 100;
                    list.insert(i, temp);
                    System.out.print(temp + " ");
        }

        list.delete(4);
        System.out.println("\n------删除第五个元素之后-------");
        for (int i = 0; i < list.size; i++) {
                     System.out.print(list.get(i) + " ");
        }
    }
}
