package tk.jingzing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.jingzing.interceptor.MyInterceptor1;
import tk.jingzing.interceptor.MyInterceptor2;

/**
 * 最后强调一点：只有经过DispatcherServlet 的请求，才会走拦截器链，我们自定义的Servlet 请求是不会被拦截的，
 * 比如我们自定义的Servlet地址 http://localhost:8080/xs/myservlet 是不会被拦截器拦截的。
 * 并且不管是属于哪个Servlet 只要复合过滤器的过滤规则，过滤器都会拦截。
 * Created by Louis Wang on 2016/6/6.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    //使用外部目录
    // 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
    //registry.addResourceHandler("/myimgs/**").addResourceLocations("file:H:/myimgs/");
}
