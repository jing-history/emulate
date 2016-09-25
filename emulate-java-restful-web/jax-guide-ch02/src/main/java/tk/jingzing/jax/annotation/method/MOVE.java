package tk.jingzing.jax.annotation.method;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;

/**
 * Created by wangyunjing on 16/9/25.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod(value = "MOVE")
@Documented
public @interface MOVE {
}
