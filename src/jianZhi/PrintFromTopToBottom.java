package jianZhi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/1/23 - 15:42
 * @Description:从上往下打印二叉树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印
 */
public class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        //Queue接口与List、Set同一级别
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode tempNode = queue.poll();//检索并删除队列头节点
            list.add(tempNode.val);//打印此节点
            if(tempNode.left != null)
                queue.offer(tempNode.left);
            if(tempNode.right != null)
                queue.offer(tempNode.right);
        }
        return list;
    }

    public static void main(String[] args) {
        PrintFromTopToBottom topToBottom = new PrintFromTopToBottom();
        TreeNode root = null;
        ArrayList list = topToBottom.PrintFromTopToBottom(root);
        /*for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }*/
    }
}
