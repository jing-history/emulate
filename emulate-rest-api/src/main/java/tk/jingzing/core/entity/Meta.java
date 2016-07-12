package tk.jingzing.core.entity;

/**
 * Meta : 封装返回给前端的metadata
 * Created by Louis Wang on 2016/7/12.
 */

public class Meta {
    private Integer code;

    private String message;

    public Meta(){

    }

    public Meta(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
