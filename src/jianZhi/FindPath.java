package jianZhi;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/1/24 - 21:48
 * @Description:二叉树中和为某一值的路径 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {
    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();

    //利用栈记录一个路径上之前走过的节点  利用递归完成关于每一个二叉子树左节点和右节点的判断
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return new ArrayList<>();
        find(root, target, 0);
        return result;
    }

    /*** 
     * @Author: ZwZ
     * @Description: 遍历二叉树将满足条件的路径所在的数组加入到返回数组中  
     * @Param: [root, target, cur] 根节点，给定待判断数值 ，栈中元素之和(难点：cur一直不是以变量的身份存在，而是以方法参数的形式存在，所以每次出栈后cur的值自动减掉栈顶值，有存储栈中元素之和的作用)
     * @return: void 
     * @Date: 2020/1/25-20:01
     */
    private void find(TreeNode root, int target, int cur) {
        //从根节点到叶节点的此路径满足
        if (cur == target && root == null) {
            result.add(new ArrayList<>(stack));
            return;
        }
        if (root == null)
            return;
        stack.push(root.val);
        find(root.left, target, cur + root.val);
        stack.pop();//将此叶子结点出栈
        if (root.left == null && root.right == null)
            return;
        stack.push(root.val);//用实际树模拟一遍，便知道为什么还要进栈一次
        find(root.right, target, cur + root.val);
        stack.pop();
    }
    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int target) {
        if(root == null)
            return result;
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null)
            result.add(new ArrayList<Integer>(list));
        FindPath2(root.left,target);
        FindPath2(root.right,target);
        list.remove(list.size() - 1);
        return result;
    }
    public static void main(String[] args) {
        FindPath findPath = new FindPath();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        ArrayList<ArrayList<Integer>> list = findPath.FindPath2(root, 22);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j));
            }
        }
    }
}
