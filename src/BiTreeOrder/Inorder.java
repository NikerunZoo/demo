package BiTreeOrder;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 19:21
 * @Description:非递归中序遍历二叉树 借助栈
 */

public class Inorder {
    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        TreeNode node = root;//当前节点指针
        Stack<TreeNode> stack = new Stack<>();
        //node != null用于一开始node指向根节点时
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode currentNode = stack.pop();
                //visit(currentNode);
                System.out.println(currentNode.val);
                node = currentNode.right;//向右子树走
            }
        }
    }

    public static void main(String[] args) {
        Inorder order = new Inorder();
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
