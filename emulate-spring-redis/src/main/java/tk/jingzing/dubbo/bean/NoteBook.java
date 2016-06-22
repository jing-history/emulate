package tk.jingzing.dubbo.bean;

import lombok.Getter;
import lombok.Setter;
import tk.jingzing.redis.annation.RedisCache;
import tk.jingzing.redis.annation.RedisFieldNotCache;
import tk.jingzing.redis.annation.RedisQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Louis Wang on 2016/6/22.
 */
@Getter
@Setter
@RedisCache
public class NoteBook implements Serializable{
    @RedisFieldNotCache
    private static final long serialVersionUID = 1L;
    @RedisFieldNotCache
    private static final String className = "NoteBook";
    @RedisFieldNotCache
    private static final String primaryKey = "noteBookId";

    private int noteBookId;
    @RedisQuery
    private String noteBookName;
    private int textSum;// 统计该笔记本下面有多少文本
    private NoteBookGroup noteBookGroup;
    @RedisQuery
    private Integer flag;
    @RedisQuery
    private Date createdate;
}
