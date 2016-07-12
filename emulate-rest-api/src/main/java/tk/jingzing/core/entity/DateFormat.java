package tk.jingzing.core.entity;

/**
 * DateFormat : 时间类型
 * Created by Louis Wang on 2016/7/12.
 */

public enum  DateFormat {

    Date("yyyy-MM-dd"), DateTime("yyyy-MM-dd HH:mm:ss"), DatePath("yyyy/MM/dd"), DatePathSingle("yyyy/M/d");

    private final String format;

    private DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
