package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/23 - 17:27
 * @Description:二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
 */
public class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if(sequence.length == 0 || sequence == null)
            return false;
        else
            return isBST(sequence,0,sequence.length - 1);
    }

    //判断是否为二叉排序
    private boolean isBST(int[] sequence, int start, int root) {
        if(start >= root)
            return true;
        int key = sequence[root];//根节点
        int i = 0;
        //找到左右子树的分界点
        for (; i < root; i++) {
            if (sequence[i] > key) {
                break;
            }
        }
        //在右子树中判断是否含有小于根节点的值
        for (int j = i; j < root; j++) {
            if (sequence[j] < key)
                return false;
        }
        return isBST(sequence,start,i-1) && isBST(sequence,i,root-1);
    }

    public static void main(String[] args) {
        int[] sequence = {5,4,3,2,1};//斜树
        VerifySquenceOfBST isBST = new VerifySquenceOfBST();
        System.out.println(isBST.VerifySquenceOfBST(sequence));
    }
}
