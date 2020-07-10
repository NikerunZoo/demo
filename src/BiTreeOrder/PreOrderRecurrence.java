package BiTreeOrder;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 22:18
 * @Description:递归先序遍历二叉树
 */
public class PreOrderRecurrence {
    public void preOrder(TreeNode root) {
        if (root == null)
            return;
        //visit(root);
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        PreOrderRecurrence order = new PreOrderRecurrence();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        order.preOrder(root);
    }
}
