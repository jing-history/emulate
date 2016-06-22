package tk.jingzing.dubbo.bean;

import lombok.Getter;
import lombok.Setter;
import tk.jingzing.redis.annation.RedisCache;
import tk.jingzing.redis.annation.RedisFieldNotCache;
import tk.jingzing.redis.annation.RedisQuery;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description: 笔记本组实体类
 * Created by Louis Wang on 2016/6/22.
 */
@Getter
@Setter
@RedisCache
public class NoteBookGroup implements Serializable {

    @RedisFieldNotCache
    private static final long serialVersionUID = 1L;
    @RedisFieldNotCache
    private static final String className = "NoteBookGroup";
    @RedisFieldNotCache
    private static final String primaryKey = "noteBookGroupId";

    private int noteBookGroupId;
    @RedisQuery
    private String noteBookGroupName;
    private int textSum;// 统计该笔记本组下面有多少文本
    @RedisQuery
    private Integer flag;
    @RedisQuery
    private Date createdate;
}
