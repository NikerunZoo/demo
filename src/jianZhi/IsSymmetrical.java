package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/3/1 - 21:14
 * @Description:判断一颗二叉树是不是对称的 判断一颗二叉树是不是对称的
 */
public class IsSymmetrical {
    public boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null)
            return true;
        return judge(pRoot.left,pRoot.right);
    }

    private boolean judge(TreeNode left, TreeNode right) {
        if(left == null && right == null)
            return true;
        if((left == null && right != null) || (left != null && right == null) || (left.val != right.val))
            return false;
        return judge(left.left,right.right) && judge(left.right,right.left);
    }

}
