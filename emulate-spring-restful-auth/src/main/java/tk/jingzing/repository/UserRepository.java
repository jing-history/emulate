package tk.jingzing.repository;

import org.springframework.data.repository.CrudRepository;
import tk.jingzing.domain.User;

/**
 * User类的CRUD操作
 * Created by Louis Wang on 2016/7/13.
 */

public interface UserRepository extends CrudRepository<User,Long>{
    public User findByUsername(String username);
}
