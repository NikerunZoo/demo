package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/20 - 20:38
 * @Description:树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构
 * ps：我们约定空树不是任意一个树的子结构
 */
public class HasSubtree {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null)
            return false;
        if(root1.val == root2.val){
            if(equalJudge(root1,root2))
                return true;
        }
        //遍历左孩子，右孩子
        return HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }
    //判断root2是否是root1子结构
    /** 
    * @Author: ZwZ
    * @Description:递归函数分两种：如果是没有返回值那么就按照王道上面给的先序，中序，后序的那种模式和框架去写
     * 如果是有返回值，那么就要按照这种模式去写，即返回 ： return equalJudge(root1.left, root2.left) && equalJudge(root1.right, root2.right);
     * 万变不离其宗的是：三步走
     * 1.递归结束条件
     * 2.递归内容
     * 3.返回值 (没有返回值就不需要去写)
    * @Param: [root1, root2] 
    * @return: boolean 
    * @Date: 2020/2/12-12:55
    */

    public boolean equalJudge(TreeNode root1, TreeNode root2) {
        //root2已经循环完毕，代表全部匹配
        if(root2 == null){
            return true;
        }
        //大树已经循环完毕，并未成功匹配
        if(root1 == null){
            return false;
        }
        //相等后判断左右孩子
        if(root1.val == root2.val){
            return equalJudge(root1.left, root2.left) && equalJudge(root1.right, root2.right);
        }
        //值不相等
        return false;
    }
}