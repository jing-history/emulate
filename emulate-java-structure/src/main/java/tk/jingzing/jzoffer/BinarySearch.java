package tk.jingzing.jzoffer;

/**
 * @Description:二分查找
 *
 * 原理：将数组分为三部分，依次是中值（所谓的中值就是数组中间位置的那个值）前，中值，中值后；将要查找的值和数组的中值进行比较，
 * 若小于中值则在中值前面找，若大于中值则在中值后面找，等于中值时直接返回。然后依次是一个递归过程，将前半部分或者后半部分继续分解为三部分。
 * 可能描述得不是很清楚，若是不理解可以去网上找。从描述上就可以看出这个算法适合用递归来实现，可以用递归的都可以用循环来实现。
 * 所以我们的实现分为递归和循环两种，可以根据代码来理解算法
 *
 * Created by Louis Wang on 2016/8/11.
 */

public class BinarySearch {
    private int rCount=0;
    private int lCount=0;

    /**
     * 获取递归的次数
     * @return
     */
    public int getrCount() {
        return rCount;
    }

    /**
     * 获取循环的次数
     * @return
     */
    public int getlCount() {
        return lCount;
    }

    /**
     * 执行递归二分查找，返回第一次出现该值的位置
     * @param sortedData	已排序的数组
     * @param start			开始位置
     * @param end			结束位置
     * @param findValue		需要找的值
     * @return				值在数组中的位置，从0开始。找不到返回-1
     */
    public int searchRecursive(int[] sortedData,int start,int end,int findValue)
    {
        rCount++;
        if(start<=end)
        {
            //中间位置
            int middle=(start+end)>>1;	//相当于(start+end)/2
            //中值
            int middleValue=sortedData[middle];

            if(findValue==middleValue)
            {
                //等于中值直接返回
                return middle;
            }
            else if(findValue<middleValue)
            {
                //小于中值时在中值前面找
                return searchRecursive(sortedData,start,middle-1,findValue);
            }
            else
            {
                //大于中值在中值后面找
                return searchRecursive(sortedData,middle+1,end,findValue);
            }
        }
        else
        {
            //找不到
            return -1;
        }
    }

    /**
     * 循环二分查找，返回第一次出现该值的位置
     * @param sortedData	已排序的数组
     * @param findValue		需要找的值
     * @return				值在数组中的位置，从0开始。找不到返回-1
     */
    public int searchLoop(int[] sortedData,int findValue)
    {
        int start=0;
        int end=sortedData.length-1;

        while(start<=end)
        {
            lCount++;
            //中间位置
            int middle=(start+end)>>1;	//相当于(start+end)/2
            //中值
            int middleValue=sortedData[middle];

            if(findValue==middleValue)
            {
                //等于中值直接返回
                return middle;
            }
            else if(findValue<middleValue)
            {
                //小于中值时在中值前面找
                end=middle-1;
            }
            else
            {
                //大于中值在中值后面找
                start=middle+1;
            }
        }
        //找不到
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs=new BinarySearch();

        int[] sortedData={1,2,3,4,5,6,6,7,8,8,9,10};
        int findValue=9;
        int length=sortedData.length;

        int pos=bs.searchRecursive(sortedData, 0, length-1, findValue);
        System.out.println("Recursice:"+findValue+" found in pos "+pos+";count:"+bs.getrCount());
        int pos2=bs.searchLoop(sortedData, findValue);

        System.out.println("Loop:"+findValue+" found in pos "+pos+";count:"+bs.getlCount());
    }
}
