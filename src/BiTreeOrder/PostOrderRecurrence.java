package BiTreeOrder;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 22:24
 * @Description:递归后序遍历二叉树
 */
public class PostOrderRecurrence {
    public void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        //visit(root);
        System.out.println(root.val);
    }

    public static void main(String[] args) {
        PostOrderRecurrence order = new PostOrderRecurrence();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        order.postOrder(root);
    }
}
