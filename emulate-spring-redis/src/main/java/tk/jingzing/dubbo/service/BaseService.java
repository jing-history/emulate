package tk.jingzing.dubbo.service;

import java.util.List;

/**
 * Created by Louis Wang on 2016/7/4.
 */

public interface BaseService<T> {
    /* 查询所有 */
    public List<T> findAll();

    /* 删除 */
    void delete(String id);

    /* 更新 */
    void update(T t);

    /**/
    void insert(T t);
}
