package zuoProgress.binaryTree;

import zuoPrimary.shu.TreeNode;

/**
 * @author - zwz
 * @date - 20-4-13-下午4:05
 * @Description:二叉树汇总一个节点可以往上走往下走，那么从节点A总能走到节点B 节点A走到节点B的距离为：A走到B的最短路径上的节点个数
 * 求一颗二叉树上的最远距离
 */
public class GetMaxDistance {
    /*
     * @Author: ZwZ
     * @Description:向外提供的求一颗二叉树最远距离的函数
     * @Date: 下午4:24 20-4-13
     * @Param: [head]
     * @return: int
     **/
    public int getMax(TreeNode head){
        return process(head).maxInstance;
    }
    /*
     * @Author: ZwZ
     * @Description:求以每个节点为头节点的二叉树的最远距离，答案一定在其中
     * 以一个节点为头结点的二叉树的最远距离有三种情况：
     * 1.此最远距离出现在左子树中
     * 2.此最远距离出现在右子树中
     * 3.此最远距离会经过当前节点
     * @Date: 下午4:09 20-4-13
     * @Param: [head]
     * @return: int
     **/
    private ReturnInfo process(TreeNode head) {
        if (head == null) {
            return new ReturnInfo(0,0);
        }
        ReturnInfo leftInfo = process(head.left);
        ReturnInfo rightInfo = process(head.right);
        int includeHeadInstance = leftInfo.high + rightInfo.high + 1;
        return new ReturnInfo(Math.max(Math.max(leftInfo.maxInstance, rightInfo.maxInstance), includeHeadInstance),
                Math.max(leftInfo.high, rightInfo.high) + 1);
    }

    /*
     * @Author: ZwZ
     * @Description:左右子树需要返回的信息
     * 返回的信息中：
     * maxInstance:最大距离
     * high:子树高度
     * 这两个信息就足以支撑递归函数中三种情况的计算所需要的数据
     * @Date: 下午4:16 20-4-13
     * @Param:
     * @return:
     **/
    public class ReturnInfo {
        int maxInstance;
        int high;
        public ReturnInfo(int maxInstance, int high) {
            this.maxInstance = maxInstance;
            this.high = high;
        }
    }
}
