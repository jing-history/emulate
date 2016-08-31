package tk.jingzing.designpattern.decorator;

/**
 * @Description:具体装饰角色
 * Created by Louis Wang on 2016/8/31.
 */

public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        // 写相关的业务代码
    }

}
