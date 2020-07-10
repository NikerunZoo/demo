package jianZhi;

import java.util.Arrays;

/**
 * @author - ZwZ
 * @date - 2020/1/11 - 14:34
 * @Description:重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，
 * 请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val){
        this.val = val;
    }
}
public class ReConstructBinaryTree {
    /*根据前序序列第一个结点确定根结点
    根据根结点在中序序列中的位置分割出左右两个子序列
    对左子树和右子树分别递归使用同样的方法继续分解*/
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if(pre.length == 0 || in.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);//根节点
        for (int i = 0; i < in.length; i++) {
            if(in[i] == pre[0]){
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                root.right = reConstructBinaryTree( Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }

    public static void main(String[] args) {
        ReConstructBinaryTree tree = new ReConstructBinaryTree();
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode root = tree.reConstructBinaryTree(pre,in);
        while (root.right != null){
            System.out.println(root.right.val);
            root = root.right;
        }
    }
}
