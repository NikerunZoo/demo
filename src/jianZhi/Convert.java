package jianZhi;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/1/27 - 14:11
 * @Description:二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向
 */
public class Convert {
    /*
     * 链表中最后一个节点的指针
     * 当使用递归，且需要用具体变量保存每次递归的结果时，
     * 不能把此变量作为递归函数的形参
     * 因为每次递归返回时此变量都会变为之前的值
     * 所以这里的变量定义在了方法的外面且不作为inOrderConvert()的形式参数
     * */
    TreeNode lastNodeList = null;

    /**
     * @Author: ZwZ
     * @Description: 在中序遍历过程中对指针进行改变 
     * @Param: [pRootOfTree] 
     * @return: jianZhi.TreeNode 
     * @Date: 2020/1/27-16:19
     */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        inOrderConvert(pRootOfTree);
        //寻找链表头节点
        while (lastNodeList != null && lastNodeList.left != null)
            lastNodeList = lastNodeList.left;
        return lastNodeList;
    }
    /**
     * @Author: ZwZ
     * @Description: 中序遍历并改变指针
     * @Param: [root] 
     * @return: void 
     * @Date: 2020/1/27-16:22
     */
    public void inOrderConvert(TreeNode root) {
        if (root == null)
            return;
        if (root != null) {
            inOrderConvert(root.left);
            root.left = lastNodeList;
            if (lastNodeList != null)
                lastNodeList.right = root;
            lastNodeList = root;
            inOrderConvert(root.right);
        }
    }
    ArrayList<TreeNode> list = new ArrayList<>();
    /**
     * @Author: ZwZ
     * @Description: 通过中序遍历将节点按大小顺序存入数组 再对数组进行操作从而改变指针
     * @Param: [pRootOfTree] 
     * @return: jianZhi.TreeNode 
     * @Date: 2020/1/27-16:19
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        inorder(pRootOfTree);
        for (int i = 1; i < list.size() - 1; i++) {
            list.get(i).left = list.get(i - 1);
            list.get(i).right = list.get(i + 1);
        }
        //首尾两个节点单独处理
        if (list.size() > 1) {
            list.get(0).right = list.get(1);
            list.get(list.size() - 1).left = list.get(list.size() - 2);
        }
        if(list.size() != 0)
            return list.get(0);
        else
            return null;
    }
    /** 
    * @Author: ZwZ
    * @Description:使用非递归中序遍历 在其过程中，改变指针方向 
    * @Param: [pRootOfTree] 
    * @return: jianZhi.TreeNode 
    * @Date: 2020/2/18-13:56
    */
    public TreeNode Convert3(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return null;
        Stack<TreeNode> stack = new Stack();
        TreeNode pre = null;
        TreeNode node = pRootOfTree;
        TreeNode head = null;//返回值
        while(!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                TreeNode currentNode = stack.pop();
                currentNode.left = pre;
                if(pre != null)
                    pre.right = currentNode;
                else
                    head = currentNode;
                pre = currentNode;
                node = currentNode.right;
            }
        }
        return head;
    }
    /**
     * @Author: ZwZ
     * @Description: 中序遍历二叉树 
     * @Param: [root] 
     * @return: java.util.ArrayList<jianZhi.TreeNode> 
     * @Date: 2020/1/27-16:24
     */
    public ArrayList<TreeNode> inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            list.add(root);
            inorder(root.right);
        }
        return list;
    }
    public static void main(String[] args) {
        Convert convert = new Convert();
        TreeNode root = new TreeNode(10);
        /*root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(14);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);*/
        TreeNode listHead = convert.Convert2(null);
        while (listHead != null) {
            System.out.println(listHead.val);
            System.out.println("----------------");
            listHead = listHead.right;
        }
    }
}
