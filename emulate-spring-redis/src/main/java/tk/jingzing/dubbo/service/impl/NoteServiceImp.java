package tk.jingzing.dubbo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import tk.jingzing.dubbo.bean.Note;
import tk.jingzing.dubbo.service.BaseService;
import tk.jingzing.dubbo.service.NoteService;
import tk.jingzing.redis.dao.RedisDao;
import tk.jingzing.redis.util.BeanField;
import tk.jingzing.redis.util.RedisCacheManager;
import tk.jingzing.redis.util.RedisCachePool;
import tk.jingzing.redis.util.RedisDataBaseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Louis Wang on 2016/7/4.
 */
@Service("NoteServiceImp")
@Slf4j
public class NoteServiceImp implements NoteService, BaseService<Note>{

    @Autowired
    RedisCacheManager redisCacheManager;

    public List<Note> findAll() {
        List<Note> noteList = new ArrayList<Note>();
        RedisCachePool pool = null;
        Jedis jedis = null;

        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            // 查询不用开启事物
            RedisDao rd = new RedisDao(jedis);
            Set<String> sortKey = rd.smembers("Note:index:noteId");
            noteList = (List<Note>) rd.getListBean(sortKey, Note.class, jedis);

            // dubbo 调用的时候防止java.sql.Blob cannot be assigned from null ，也就是blob字段不能为空
            delalBlob(noteList);
        } catch (Exception e) {
            log.error(" List<Note> findAll()查询失败！" + e.getLocalizedMessage());
        }finally {
            log.info("回收jedis连接");
            pool.returnResource(jedis);
        }
        return noteList;
    }

    public void delete(String id) {
        RedisCachePool pool = null;
        Jedis jedis = null;
        RedisDao rd = null;

        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            Object note = RedisDao.getBean("Note:" + id, Note.class, jedis);

            if(null != note){
                // 查询之后开启事物
                Transaction transation = jedis.multi();

                rd = new RedisDao(transation);
                rd.delSingleDataFromRedis(note, rd.getBeanField(note));

                /* 处理之后的数据库sql日志处理 */
                String logs = "delete from tcnote where note_id=" + id;
                rd.pubishLog(logs);
                rd.log(logs);

                transation.exec();
            }
        } catch (Exception e) {
            log.error(" delete(String id) 删除失败！" + e.getLocalizedMessage());
        }
        finally {
            log.info("回收jedis连接");
            pool.returnResource(jedis);
        }
    }

    /**
     * 更新直接调用删除，然后再插入
     */
    public void update(Note newNote) {
        RedisCachePool pool = null;
        Jedis jedis = null;
        RedisDao rd = null;

        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();

            // 获取原来redis里面存储的note
            Object orldNote = RedisDao.getBean("Note:" + newNote.getNoteId(), Note.class, jedis);
            if (null != orldNote) {
                // 查询之后开启事物
                Transaction transation = jedis.multi();
                rd = new RedisDao(transation);
                BeanField beanField = rd.getBeanField(orldNote);

                // // 先删除原来的
                // rd.delSingleDataFromRedis(orldNote, beanField);
                // // 再插入新修改的note
                // rd.insertSingleDataToredis(newNote, beanField);

                rd.updateSingleFromToredis(orldNote, newNote, beanField);

                /* 处理之后的数据库sql日志处理 */
                String logs = genSql(newNote);
                rd.pubishLog(logs);
                rd.log(logs);
                transation.exec();
            }
        } catch (Exception e) {
            log.error(" update(Note note) 失败！" + e.getLocalizedMessage());
        }
        finally {
            // log.info("回收jedis连接");
            pool.returnResource(jedis);
        }
    }

    public void insert(Note note) {
        RedisCachePool pool = null;
        Jedis jedis = null;
        RedisDao rd = null;
        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();

            // 获取redis里面最大的主键值
            Set<String> sortKey = RedisDao.getRevrangeSortSet("Note:sort:noteId", 0, 0, jedis);
            for (String id : sortKey) {
                // 新增的主键赋值
                note.setNoteId(Integer.parseInt(id) + 1);
                break;
            }

            Transaction transation = jedis.multi();
            rd = new RedisDao(transation);
            BeanField beanField = rd.getBeanField(note);
            // 插入新增的
            rd.insertSingleDataToredis(note, beanField);

			/* 处理之后的数据库sql日志处理 */
            String logs = insertSql(note);
            rd.pubishLog(logs);
            rd.log(logs);
            transation.exec();

        } catch (Exception e) {
            log.error(" insert(Note note) 失败！" + e.getLocalizedMessage());
        }
        finally {
            log.info("回收insert==>jedis连接");
            pool.returnResource(jedis);
        }
    }

    public Note queryById(String i) {
        Note note = new Note();
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        Jedis jedis = pool.getResource();
        // 查询不用开启事物
        RedisDao rd = new RedisDao(jedis);
        note = (Note) rd.getBean("Note:" + i, note.getClass(), jedis);
        pool.returnResource(jedis);

        // dubbo 调用的时候防止java.sql.Blob cannot be assigned from null ，也就是blob字段不能为空
        note.setBlobContent(null);
        return note;
    }

    public List<Note> queryParamAnd(Note note) {
        List<Note> noteList = new ArrayList<Note>();
        RedisCachePool pool = null;
        Jedis jedis = null;
        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            // 查询不用开启事物
            RedisDao rd = new RedisDao(jedis);

            String[] kes = genKeys(note);// 获取需要查询的key的值
            Set<String> sortKey = rd.sinter(kes);// 获取交集的主键
            noteList = (List<Note>) rd.getListBean(sortKey, Note.class, jedis);

            // dubbo 调用的时候防止java.sql.Blob cannot be assigned from null ，也就是blob字段不能为空
            delalBlob(noteList);
        } catch (Exception e) {
            log.error(" List<Note> findAll()查询失败！" + e.getLocalizedMessage());
        }
        finally {
            log.info("回收jedis连接");
            pool.returnResource(jedis);
        }
        return noteList;
    }

    private void delalBlob(List<Note> noteList) {
        for (Note note : noteList) {
            note.setBlobContent(null);
        }
    }

    /**
     * @Description: 组织sql
     * @param note
     * @return:void
     */
    private String genSql(Note note) {
        // 组装sql
        StringBuilder sb = new StringBuilder();
        sb.append(" update tcnote  set ");

        if (note.getAuthorName() != null) {
            sb.append(" author_name='" + note.getAuthorName() + "', ");
        }
        if (note.getFromUrl() != null) {
            sb.append(" from_Url='" + note.getFromUrl() + "', ");
        }
        if (note.getNoteName() != null) {
            sb.append(" note_name='" + note.getNoteName() + "', ");
        }
        // flag=0防止note里面字段都是空的
        sb.append(" flag=0 where note_id= " + note.getNoteId());

        return sb.toString();
    }

    /**
     * @Description: 组装key值
     * @param note
     * @return:void
     */
    private String[] genKeys(Note note) {
        String[] string = null;
        StringBuilder sb = new StringBuilder();

        if (note.getAuthorName() != null) {
            sb.append("," + "Note:authorName:" + note.getAuthorName());
        }
        if (note.getFromUrl() != null) {
            sb.append("," + "Note:fromUrl:" + note.getFromUrl() + ",");
        }
        if (note.getFlag() != null) {
            sb.append("," + "Note:flag:" + note.getFlag() + ",");
        }

        if (null != sb.toString()) {
            // 去除第一个逗号
            String replaceString = sb.toString().replaceFirst(",", "");
            string = replaceString.split(",");
        }
        return string;
    }

    private String insertSql(Note note) {
        // 组装sql
        StringBuilder sb = new StringBuilder();
        sb.append("insert into tcnote (note_id,note_name,author_name,from_url,flag,noteBook,noteBookGroup) values ( ");
        sb.append(note.getNoteId() + ", ");
        sb.append("'" + note.getNoteName() + "', ");
        sb.append("'" + note.getAuthorName() + "', ");
        sb.append("'" + note.getFromUrl() + "', ");
        sb.append(note.getFlag() + ", ");
        sb.append(" 1, ");
        sb.append(" 1 )");
        return sb.toString();
    }
}
