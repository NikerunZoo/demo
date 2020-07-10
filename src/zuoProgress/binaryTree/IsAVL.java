package zuoProgress.binaryTree;

import zuoPrimary.shu.TreeNode;

/**
 * @author - zwz
 * @date - 20-4-14-下午7:33
 * @Description:判断一颗树是否是一棵平衡二叉树
 */
public class IsAVL {
    public boolean isAVL(TreeNode head) {
        ReturnInfo info = process(head);
        return info.isAVL;
    }
    private ReturnInfo process(TreeNode head) {
        if (head == null) {
            return new ReturnInfo(true, 0);
        }
        ReturnInfo leftInfo = process(head.left);
        ReturnInfo rightInfo = process(head.right);
        if ((!leftInfo.isAVL) || (!rightInfo.isAVL) || Math.abs(leftInfo.high - rightInfo.high) > 1)
            return new ReturnInfo(false, Integer.MIN_VALUE);
        else
            return new ReturnInfo(true,Math.max(leftInfo.high,rightInfo.high) + 1);

    }

    private class ReturnInfo {
        boolean isAVL;
        int high;

        public ReturnInfo(boolean isAVL, int high) {
            this.isAVL = isAVL;
            this.high = high;
        }
    }
}
