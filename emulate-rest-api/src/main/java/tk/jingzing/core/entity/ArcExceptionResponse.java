package tk.jingzing.core.entity;

/**
 * ArcExceptionResponse : 异常响应, 消息将封装至返回json的meta中
 * Created by Louis Wang on 2016/7/12.
 */

public class ArcExceptionResponse {

    private Meta meta;

    public ArcExceptionResponse() {
    }

    public ArcExceptionResponse(Meta meta) {
        this.meta = meta;
    }

    public static ArcExceptionResponse create(Integer code,String messgae){
        Meta exceptionMeta = new Meta(code,messgae);
        return new ArcExceptionResponse(exceptionMeta);
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
