package tk.jingzing.redis.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 需要缓存到redis实体类
 * java注解 http://www.tuicool.com/articles/2MRZJn
 * Created by Louis Wang on 2016/6/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RedisCache {
    public boolean need() default true;
}
