package tk.jingzing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.jingzing.Exception.MyException;

/**
 * Created by wangyunjing on 16/6/9.
 */
@Controller
@RequestMapping("/exp")
public class ExceptionController {

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
