package tk.jingzing.jzoffer;

/**
 * http://www.cnblogs.com/hexiaochun/archive/2012/09/03/2668324.html
 * @Description:快速排序算法Java实现
 *
 * 算法思想:通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 *
 * 实现思路:
 * ①以第一个关键字 K 1 为控制字，将 [K 1 ,K 2 ,…,K n ] 分成两个子区，使左区所有关键字小于等于 K 1 ，
 * 右区所有关键字大于等于 K 1 ，最后控制字居两个子区中间的适当位置。在子区内数据尚处于无序状态。
 * ②把左区作为一个整体，用①的步骤进行处理，右区进行相同的处理。（即递归）
 * ③重复第①、②步，直到左区处理完毕。
 *
 * Created by Louis Wang on 2016/8/10.
 */
public class QuickSort {

    public void quick_sort(int[] arrays, int length){
        if(null == arrays || length < 1){
            System.out.println("QuickSort.quick_sort:input error！！！");
            return;
        }
        _quick_sort(arrays,0,length - 1);
    }

    private void _quick_sort(int[] arrays, int start, int end) {
        if(start >= end){
            return;
        }

        int i = start;
        int j = end;
        int value = arrays[i];
        boolean flag = true;

        while (i != j){
            if(flag){
                if(value > arrays[j]){
                    swap(arrays,i,j);
                    flag = false;
                }else {
                    j--;
                }
            }else {
                if(value < arrays[i]){
                    swap(arrays,i,j);
                    flag = true;
                }else {
                    i++;
                }
            }
        }
        snp(arrays);
        _quick_sort(arrays,start,j-1);
        _quick_sort(arrays,i+1,end);
    }

    private void snp(int[] arrays) {
        for (int i = 0; i < arrays.length ; i++){
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }

    private void swap(int[] arrays, int i, int j) {
        int temp;
        temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] a = { 49, 38, 65, 12, 45, 5 };
        q.quick_sort(a,6);
    }
}
