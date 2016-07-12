package tk.jingzing.web.bean;

/**
 * SelectBean : 批量提供Select数据, 为前端Select元素提供数据
 * Created by Louis Wang on 2016/7/12.
 */

public class SelectBean {

    /**
     * Select中的value
     */
    private String value;

    /**
     * Select中显示的文本
     */
    private String text;

    public SelectBean() {
    }

    public SelectBean(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
