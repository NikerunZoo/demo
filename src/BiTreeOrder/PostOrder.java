package BiTreeOrder;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 20:22
 * @Description:非递归后序遍历二叉树
 * 一个节点，如果是遍历完左子树再回到这个节点处，那么就不需要出栈
 * 如果是遍历完右子树又返回到这个节点，那么就需要出栈  这种情况下，在返回到这个节点前访问的那个节点就是本节点的右孩子
 */
public class PostOrder {
    public void postOrder(TreeNode root){
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;//遍历指针
        TreeNode pre = null;//指向前一个节点
        //node != null用于进入if  !stack.isEmpty()用于进入else
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                //当本节点没有右孩子或刚遍历完本节点的右孩子，那么接下来就要遍历本节点
                if(node.right == null || node.right == pre){
                    //visit(currentNode);
                    System.out.println(node.val);
                    pre = node;
                    node = null;//接下来while中第一个if不进入而直接进入else，确保栈顶节点node不会再次入栈
                }else{//本节点的右孩子不为空并且上一次遍历的节点不是它的右孩子
                    stack.push(node);//此时node不应该出栈，所以再将刚刚出栈的node压入栈中
                    node = node.right;
                    stack.push(node);//将右孩子压入栈中  重新进行下一轮循环(类似于递归 因为每一颗子树也是一颗二叉树)
                    node = node.left;
                }
            }

        }
    }

    public static void main(String[] args) {
        PostOrder order = new PostOrder();
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
