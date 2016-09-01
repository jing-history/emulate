package tk.jingzing.demo;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @Description:配置web service bean
 *
 * 1.这里Spring WS使用了不同的servlet类型来处理SOAP消息：MessageDispatcherServlet。
 * 注入及设置MessageDispatcherServlet给ApplicationContext是非常重要的。如果不这样做，Spring WS无法自动检测到Spring bean。
 * 2.通过给dispatcherServlet bean命名，替代了Spring Boot中默认的DispatcherServlet bean。
 * 3.DefaultMethodEndpointAdapter配置了注解驱动的Spring WS编程模型。这使得使用前面提过的诸如@Endpoint等各种各样的注解成为可能。
 * 4.DefaultWsdl11Defination使用XsdSchema暴露了一个标准的WSDL 1.1。
 *
 * 请注意你需要为MessageDispatcherServlet及DefaultWsdl11Definition制定bean名称，这是非常重要的。Bean名称决定了生成的WSDL文件在哪个web service是可用的。
 * 在本例中，WSDL可通过http://<host>:<port>/ws/countries.wsdl来访问。
 * 该配置也使用了WSDL位置servlet转化servlet.setTransformWsdlLocations(true)。
 * 如果你访问http://localhost:8080/ws/countries.wsdl，soap：address将拥有正确的值。如果你使用本机的公共IP来访问该WSDL，你将看到的是IP。
 *
 * Created by Louis Wang on 2016/9/1.
 */

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
}
