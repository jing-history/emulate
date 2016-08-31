package tk.jingzing.designpattern.decorator;

/**
 * @Description:装饰角色
 * Created by Louis Wang on 2016/8/31.
 */

public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void sampleOperation() {
        // 委派给构件
        component.sampleOperation();
    }
}
