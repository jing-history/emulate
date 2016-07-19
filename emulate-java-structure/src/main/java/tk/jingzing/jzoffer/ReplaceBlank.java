package tk.jingzing.jzoffer;

/**
 * 替换空格
 * 如果从前面往后面替换，时间复杂度为O(n2)
 * 如果从后面往前面替换，时间复杂度为O(n)
 * Created by Louis Wang on 2016/7/19.
 */

public class ReplaceBlank {


    /*length 为字符数组string的总容量*/
    public static void ReplaceBlank(char string[], int length){
        if(string == null && length <= 0)
            return;

    /*originalLength 为字符串string的实际长度*/
        int originalLength = 0;
        int numberOfBlank = 0;
        int i = 0;
        while(string[i] != '\0')
        {
            ++ originalLength;

            if(string[i] == ' ')
                ++ numberOfBlank;

            ++ i;
        }

    /*newLength 为把空格替换成'%20'之后的长度*/
        int newLength = originalLength + numberOfBlank * 2;
        if(newLength > length)
            return;

        int indexOfOriginal = originalLength;
        int indexOfNew = newLength;
        while(indexOfOriginal >= 0 && indexOfNew > indexOfOriginal)
        {
            if(string[indexOfOriginal] == ' ')
            {
                string[indexOfNew --] = '0';
                string[indexOfNew --] = '2';
                string[indexOfNew --] = '%';
            }
            else
            {
                string[indexOfNew --] = string[indexOfOriginal];
            }

            -- indexOfOriginal;
        }
    }

    public static void test(String testName, char string[], int length, String expected){
        if(testName != null)
            System.out.printf("%s begins: ", testName);

        ReplaceBlank(string, length);

        if(expected == null && string == null)
             System.out.printf("passed.\n");
        else if(expected == null && string != null)
             System.out.printf("failed.\n");
        /*else if(strcmp(string, expected) == 0)
             System.out.printf("passed.\n");*/
        else
             System.out.printf("failed.\n");
    }

    public static void main(String[] args) {
        int length = 100;
        String str = "hello world";
        char string[] = str.toCharArray();
        test("Test1", string, length, "hello%20world");
    }
}
