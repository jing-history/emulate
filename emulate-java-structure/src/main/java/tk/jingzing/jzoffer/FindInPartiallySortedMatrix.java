package tk.jingzing.jzoffer;

/**
 * 二维数组中查找
 * @do 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的
 * 一个二维数组和一个整数，判断数组中是否含有该整数。
 * 1 2 8 9
 * 2 4 9 12
 * 4 7 10 13
 * 6 8 11 15
 *
 *
 * Created by Louis Wang on 2016/7/18.
 */

public class FindInPartiallySortedMatrix {
    static {

    }

    /**
     * @todo: 规律是首先选取数组中右上角数字，如果该数字等于要查找的数字，查找过程结束；
     * 如果该数字大于要查找的数字，剔除这个数字所在的列；如果该数字小于要查找的数字，
     * 剔除这个数字所在的行。
     * @param matrix
     * @param rows
     * @param columns
     * @param number
     * @return
     */
   public boolean find(int[][] matrix, int rows, int columns, int number){
       boolean found = false;

       if(matrix != null && rows > 0 && columns > 0)
       {
           int row = 0;
           int column = columns - 1;
           while(row < rows && column >=0)
           {
               if(matrix[row][column] == number)
               {
                   found = true;
                   break;
               }
               else if(matrix[row][column] > number)
                   -- column;
               else
                   ++ row;
           }
       }

       return found;
   }

    public static void main(String[] args) {
        int[][] arrys = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        FindInPartiallySortedMatrix test = new FindInPartiallySortedMatrix();
        System.out.println(test.find(arrys, 4, 4, 18));
    }
}
