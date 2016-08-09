package tk.jingzing;

/**
 * @Description:what to do
 * Created by Louis Wang on 2016/8/1.
 */

public class TestSplit {

    public static void main(String[] args) {
        String aa = "aa__cc";
        String[] arr = aa.split("_");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("TestSplit.main:" + arr.length + "  " + arr[i]);
        }
    }
}
