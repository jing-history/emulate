package tk.jingzing.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @Description:创建国家服务终端 为了创建一个service endpoint，x需要一个pojo对象，以及一些Spring WS注解来处理SOAP请求
 *
 * @Endpoint 向Spring WS注册了该类为一个处理来临的SOAP消息的潜在对象。
 * @PayloadRoot 被Spring WS用来根据消息的命名空间及localPart来选择处理该请求的方法。
 * @RequestPayload 指明来临的消息将被映射到该方法的request参数。
 * @ResponsePayload 注解将使得Spring WS将返回值与响应负载映射起来。
 *
 * Created by Louis Wang on 2016/9/1.
 */
@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
