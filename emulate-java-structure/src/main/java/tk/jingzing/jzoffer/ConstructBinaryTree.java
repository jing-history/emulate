package tk.jingzing.jzoffer;

/**
 * @url:http://blog.csdn.net/jsqfengbao/article/details/47088947
 *
 * @Description:what 剑指Offer面试题6（Java版）：重建二叉树
 * 前序遍历：先访问根节点，在访问左子节点，最后访问右子节点
 * 中序遍历：先访问左子节点，在访问根节点，最后访问右子节点
 * 后序遍历：先访问左子节点，在访问右子节点，最后访问根节点
 *
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重新构造出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中不包含重复的数字。
 * 例如输入的前序遍历序列为｛1，2，4，7，3，5，6，8｝和中序遍历为{4,7,2,1,5,3,6,8},
 * 则重建出二叉树并输出它的头结点。
 *
 * Created by Louis Wang on 2016/7/25.
 */

public class ConstructBinaryTree {

    /**
     *
     * @param preOrder 前序遍历数组
     * @param inOrder  中序遍历数组
     * @param length   数组长度
     * @return 根节点
     */
    public static BinaryTreeNode Construct(int[] preOrder,int[] inOrder,int length){
        if (preOrder == null || inOrder == null || length <= 0) {
            return null;
        }
        try {
            return ConstructCore(preOrder, 0, preOrder.length - 1, inOrder, 0,inOrder.length - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param preOrder          前序遍历序列
     * @param startPreIndex     前序序列开始位置
     * @param endPreIndex       前序序列结束位置
     * @param inOrder           中序遍历序列
     * @param startInIndex      中序序列开始位置
     * @param endInIndex        中序序列结束位置
     * @return
     */
    public static BinaryTreeNode ConstructCore(int[] preOrder,int startPreIndex, int endPreIndex,
                                               int[] inOrder,int startInIndex, int endInIndex) throws InvalidPutException {

        int rootValue = preOrder[startPreIndex];
        System.out.println("rootValue = " + rootValue);
        BinaryTreeNode root = new BinaryTreeNode(rootValue);

        // 只有一个元素
        if (startPreIndex == endPreIndex) {
            if (startInIndex == endInIndex
                    && preOrder[startPreIndex] == inOrder[startInIndex]) {
                System.out.println("only one element");
                return root;
            } else {
                throw new InvalidPutException();
            }
        }

        // 在中序遍历中找到根结点的索引
        int rootInIndex = startInIndex;

        while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
            ++rootInIndex;
        }

        if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootValue) {
            throw new InvalidPutException();

        }

        int leftLength = rootInIndex - startInIndex;

        int leftPreOrderEndIndex = startPreIndex + leftLength;

        if (leftLength > 0) {
            // 构建左子树
            root.leftNode = ConstructCore(preOrder, startPreIndex + 1,
                    leftPreOrderEndIndex, inOrder, startInIndex,
                    rootInIndex - 1);
        }

        if (leftLength < endPreIndex - startPreIndex) {
            // 右子树有元素,构建右子树
            root.rightNode = ConstructCore(preOrder, leftPreOrderEndIndex + 1,
                    endPreIndex, inOrder, rootInIndex + 1, endInIndex);
        }
        return root;
    }

    public static void printPreOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.value + " ");
        }

        if (root.leftNode != null) {
            printPreOrder(root.leftNode);
        }

        if (root.rightNode != null) {
            printPreOrder(root.rightNode);
        }
    }

    static class InvalidPutException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public static void main(String[] args) {
        ConstructBinaryTree test = new ConstructBinaryTree();
        int[] preOrder={1,2,4,7,3,5,6,8};
        int[] inOrder={4,7,2,1,5,3,8,6};
        printPreOrder(Construct(preOrder, inOrder, preOrder.length));
    }
}
