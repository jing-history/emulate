package tk.jingzing.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by Louis Wang on 2016/6/3.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    // 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
    @Value("${application.message:Hello World}")
    private String hello = "Hello World";

    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     *
     */
    @RequestMapping("/index")
    public ModelAndView  index(){
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        ModelAndView mav = new ModelAndView("page/index");
        mav.addObject("time", new Date());
        mav.addObject("message", this.hello);
        return mav;
    }

    /**
     * 响应到JSP页面page1（可以直接使用Model封装内容，直接返回页面字符串）
     * @param model
     * @return
     */
    @RequestMapping("/page2")
    public String page2(Model model){
        // 页面位置 /WEB-INF/jsp/page/page2.jsp
        model.addAttribute("content", hello + "（第二种）");
        return "page/page1";
    }
}
