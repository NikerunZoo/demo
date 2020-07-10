package zuoProgress.binaryTree;

import zuoPrimary.shu.TreeNode;

/**
 * @author - zwz
 * @date - 20-4-13-下午3:03
 * @Description:求一颗二叉树中最大搜索二叉子树中的节点数
 */
public class BiggestSubBSTInTree {
    /*
     * @Author: ZwZ
     * @Description:返回类型
     * @Date: 下午3:12 20-4-13
     **/
    private static class ReturnInfo{
        public int size;//最大二叉搜索树的节点数
        public TreeNode head;//最大二叉搜索树的头节点
        public int min;//整个子树的最小值
        public int max;//整个子树的最大值
        public ReturnInfo(int size, TreeNode head,int min,int max) {
            this.size =size;
            this.head = head;
            this.min = min;
            this.max = max;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:求出以每个节点为头结点的最大二叉搜索树的长度，答案一定在其中
     * 一个树的最大搜索二叉树可能来自于：
     * 1.左子树上的某子树；
     * 2.右子树上的某子树；
     * 3.当左子树和右子树整颗树都是二叉搜索树并且左子树的最大值小于当前节点值，
     * 右子树的最小值大于当前节点值的时候，整颗树是最大搜索二叉树
     * @Date: 下午3:21 20-4-13
     * @Param: [head]
     * @return: zuoProgress.binaryTree.BiggestSubBSTInTree.ReturnType
     **/
    public ReturnInfo biggestSubBST(TreeNode head){
        if(head == null)
            return new ReturnInfo(0,null,Integer.MAX_VALUE, Integer.MIN_VALUE);
        ReturnInfo leftInfo = biggestSubBST(head.left);
        ReturnInfo rightInfo = biggestSubBST(head.right);
        int includeHeadSize = 0;
        //第三种情况
        if(leftInfo.head == head.left && rightInfo.head == head.right && leftInfo.max < head.val && rightInfo.min > head.val)
            includeHeadSize = leftInfo.size + rightInfo.size + 1;
        int maxSize = Math.max(Math.max(leftInfo.size,rightInfo.size),includeHeadSize);
        TreeNode maxHead = leftInfo.size > rightInfo.size ? leftInfo.head : rightInfo.head;
        if(maxSize == includeHeadSize)
            maxHead = head;
        return new ReturnInfo(maxSize,maxHead,Math.min(Math.min(leftInfo.min,rightInfo.min),head.val),
                Math.max(Math.max(leftInfo.max,rightInfo.max),head.val));
    }
}
