package zuoProgress.MonotionicStack;

/**
 * @author - zwz
 * @date - 20-4-9-下午10:20
 * @Description:构造数组的MaxTree
 * 一个数组中的MaxTree定义如下：
 *  数组必须没有重复元素；
 *  MaxTree是二叉树，数组中的每一个值对应一个二叉树节点；
 * 包括MaxTree树在内且在其中的每一棵子树上，值最大的节点都是树的头；
 * 给定一个没有重复元素的数组arr,写出生成该数组的MaxTree的函数
 * 要求时间复杂度O(N)
 */
public class MaxTree {
    private class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:建立一个大根堆 然后再遍历一次数组将大根堆构造成完全二叉树
     * @Date: 下午10:27 20-4-9
     * @Param: [arr]
     * @return: zuoProgress.MonotionicStack.MaxTree.TreeNode
     **/
    public TreeNode getMaxTree1(int[] arr){
        //...
        return null;
    }
    /*
     * @Author: ZwZ
     * @Description:使用单调栈结构
     * 如果一个节点左边和右边都没有比它大的值，那么它就是头结点
     * 如果左边没有比它大的值，右边有，那么它就挂在右边比他大的值的下边
     * 如果左边有右边没有，那么它挂在左边那个值的下面
     * 如果左右都有的话，就挂在数值较小的那个数下面
     * @Date: 下午2:04 20-4-10
     * @Param: [arr]
     * @return: zuoProgress.MonotionicStack.MaxTree.TreeNode
     **/
    public TreeNode getMaxTree2(int[] arr){

        return null;
    }
}
