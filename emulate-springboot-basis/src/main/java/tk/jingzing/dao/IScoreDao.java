package tk.jingzing.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tk.jingzing.entity.Score;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wangyunjing on 16/6/9.
 */
public interface IScoreDao extends CrudRepository<Score, Integer> {

    //注意，如果你其中使用了修改、新增、删除操作，则必须要在接口上面或者对应的方法上面添加 @Transactional 注解，否则会抛出异常。
    @Transactional
    @Modifying
    @Query("update Score t set t.score = :score where t.id = :id")
    int updateScoreById(@Param("score") float score, @Param("id") int id);

    @Query("select t from Score t ")
    List<Score> getList();
}
