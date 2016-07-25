package tk.jingzing.jzoffer;

/**
 * @Description:二叉树 和 ConstructBinaryTree 一起使用
 * 总结：
 * 在函数ConstructCore中，我们先根据先序遍历的第一个数字创建根节点，
 * 接下来在中序遍历中找到根节点的位置，这样就能确定左右子树节点的数量。
 * 在前序遍历和中序遍历的序列中划分左右子树节点的值之后，我们就可以递归调用函数ConstructCore，去分别构建它的左右子树。
 * Created by Louis Wang on 2016/7/25.
 */

public class BinaryTreeNode {
    public int value;
    public BinaryTreeNode leftNode;
    public BinaryTreeNode rightNode;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int value) {
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;
    }
}
