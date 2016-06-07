package tk.jingzing.config;

/**
 * 这些资源都是打包在jar包中的，然后实际应用中，我们还有很多资源是在管理系统中动态维护的，并不可能在程序包中，对于这种随意指定目录的资源
 * 以增加 /myres/* 映射到 classpath:/myres/*
 * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用。
 * 如果我们将/myres/* 修改为 /* 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations 添加目录
 * Created by Louis Wang on 2016/6/7.
 */

/*@Configuration
public class WebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myres*//**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }

}*/
