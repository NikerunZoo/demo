package BiTreeOrder;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 22:22
 * @Description:递归中序遍历二叉树 1.写递归出口
 * 2.将问题分解后，书写递归式，即 将问题分解成一个个的小问题
 * 3.递归返回值
 */
public class InOrderRecurrence {
    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        //visit(root);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        InOrderRecurrence order = new InOrderRecurrence();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        order.inOrder(root);
    }
}
