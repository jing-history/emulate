package tk.jingzing.development.thread.ifeve;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:死锁是两个或更多线程阻塞着等待其它处于死锁状态的线程所持有的锁。
 * Created by Louis Wang on 2016/7/28.
 */

public class TreeNode {

    TreeNode parent   = null;
    List children = new ArrayList();

    public synchronized void addChild(TreeNode child){
        if(!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(TreeNode child){
        if(!this.children.contains(child)){
            this.children.add(child);
        }
    }

    public synchronized void setParent(TreeNode parent){
        this.parent = parent;
        parent.addChildOnly(this);
    }

    public synchronized void setParentOnly(TreeNode parent){
        this.parent = parent;
    }
}
