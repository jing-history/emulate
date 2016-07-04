package tk.jingzing.redis.synchronize;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ClassName:RedisUpdateToDataBase.java
 * @Description: redis异步更新到数据库
 * Created by Louis Wang on 2016/7/4.
 */
@Service
@Slf4j
@Transactional
public class RedisUpdateToDataBase {

    @Autowired
    SessionFactory sessionFactory;

    public boolean excuteUpdate(List<String> list) {
        try{
            Session session = sessionFactory.getCurrentSession();
            for (String string : list) {
                session.createSQLQuery(string).executeUpdate();
            }
        }catch (Exception e){
            log.info("redis更新到数据库失败！" + e.getLocalizedMessage());
        }
        return false;
    }
}
