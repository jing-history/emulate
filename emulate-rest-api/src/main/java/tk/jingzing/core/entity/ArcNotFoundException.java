package tk.jingzing.core.entity;

/**
 * ArcNotFoundException : 查询时如果未查找到相关数据, 抛出该异常
 * Created by Louis Wang on 2016/7/12.
 */

public class ArcNotFoundException extends RuntimeException {

    public ArcNotFoundException() {
    }

    public ArcNotFoundException(String message) {
        super(message);
    }
}
