package tk.jingzing.designpattern.decorator;

/**
 * @Description:装饰模式又名包装(Wrapper)模式。装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案
 * 参考：http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 * Created by Louis Wang on 2016/8/31.
 */
//抽象构件角色
public interface Component {

    public void sampleOperation();
}
