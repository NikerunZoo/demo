package zuoProgress.binaryTree;

import zuoPrimary.shu.TreeNode;

/**
 * @author - zwz
 * @date - 20-4-13-下午2:14
 * @Description:得到一棵二叉树中最小值和最大值
 */

public class GetMaxAndMin {
    public void printMaxAndMin(TreeNode head) {
        ReturnType res = get(head);
        System.out.println("min = " + res.min);
        System.out.println("max = " + res.max);
    }

    /*
     * @Author: ZwZ
     * @Description:左右子树返回值的结构
     * 即每个子树的最大值和最小值
     * @Date: 下午2:17 20-4-13
     **/
    private class ReturnType {
        private int min;
        private int max;

        public ReturnType(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    /*
     * @Author: ZwZ
     * @Description: 返回一棵树中的最大值和最小值
     * 思路：
     * 1.得到左子树的最小值和最大值
     * 2.得到右子树的最小值和最大值
     * 判断左右子树的最小值和当前节点的值哪个更小，即作为结果
     * 判断左右子树的最大值和当前节点的值哪个更大，即作为结果
     * @Date: 下午2:18 20-4-13
     * @Param: [head]
     * @return: zuoProgress.binaryTree.GetMaxAndMin.ReturnType
     **/
    private ReturnType get(TreeNode head) {
        //最小值返回系统最大，最大值返回系统最小 从而做到不影响父节点决策
        if (head == null)
            return new ReturnType(Integer.MAX_VALUE, Integer.MIN_VALUE);
        ReturnType leftType = get(head.left);//左子树上信息
        ReturnType rightType = get(head.right);//右子树上信息
        return new ReturnType(Math.min(Math.min(leftType.min, rightType.min), head.val),
                Math.max(Math.max(leftType.max, rightType.max), head.val));
    }

    public static void main(String[] args) {
        GetMaxAndMin getMaxAndMin = new GetMaxAndMin();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(88);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        getMaxAndMin.printMaxAndMin(root);
    }
}
