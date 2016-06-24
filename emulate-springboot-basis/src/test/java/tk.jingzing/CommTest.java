package tk.jingzing;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Louis Wang on 2016/6/17.
 */

public class CommTest {
    public static void main(String[] args) {

        DecimalFormat AMOUNT_SPLIT_FORMAT = new DecimalFormat("#,##,##0.00");

        System.out.println("CommTest.main:" + AMOUNT_SPLIT_FORMAT.format(new BigDecimal(1102.1570)));
    }
}
