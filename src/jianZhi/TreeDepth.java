package jianZhi;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/2/6 - 10:20
 * @Description:二叉树的深度 输入一棵二叉树，求该树的深度
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
 */
public class TreeDepth {
    /**
     * @Author: ZwZ
     * @Description:递归 
     * 书写递归不需要太仔细照顾整个过程，分两步：
     * 1.写递归出口
     * 2.将问题分解后，书写递归式，即 将问题分解成一个个的小问题
     * 3.递归返回值
     * 例如这里：high(root) = max{high(root.left),high(root.right)} + 1
     * @Param: [root] 
     * @return: int 
     * @Date: 2020/2/6-10:35
     */
    public int TreeDepth1(TreeNode root) {
        //递归出口
        if (root == null)
            return 0;
       /* int leftHigh = 0;
        int rightHigh = 0;
        leftHigh = TreeDepth1(root.left);
        rightHigh = TreeDepth1(root.right);
        if(leftHigh > rightHigh)
            return leftHigh+1;
        else
            return rightHigh+1;*/
        //上边注释代码的另外一种表现形式
        return Math.max(TreeDepth1(root.left), TreeDepth1(root.right)) + 1;
    }
    /** 
    * @Author: ZwZ
    * @Description: 层次遍历（借助队列）
    * @Param: [root] 
    * @return: int 
    * @Date: 2020/2/6-10:57
    */
    public int TreeDepth2(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int high = 0;
        int size;//用size控制high增长的次数和时机(同一层的元素没有完全退出队列的时候high不可以增加)
        TreeNode node;
        while(queue.size() != 0){
            size = queue.size();//队列长度
            while(size != 0){
                node = queue.poll();
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                size--;//当size==0时说明同一层的元素遍历完成
            }
            high++;
        }
        return high;
    }
    public static void main(String[] args) {
        TreeDepth treeDepth = new TreeDepth();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(6);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(7);
        root.right.right.left.right = new TreeNode(4);
        root.right.right.left.right.left = new TreeNode(3);
        System.out.println(treeDepth.TreeDepth2(root));
    }
}
