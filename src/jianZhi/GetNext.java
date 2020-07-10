package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 12:59
 * @Description:二叉树的下一个结点 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;//父节点

    TreeLinkNode(int val) {
        this.val = val;
    }
}

public class GetNext {
    ArrayList<TreeLinkNode> list = new ArrayList();//存储中序遍历结果

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        TreeLinkNode root = pNode;
        //寻找父节点
        while (root != null) {
            if (root.next != null)
                root = root.next;
            else
                break;
        }
        inOrder(root);
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i) == pNode)
                break;
        }
        return i == list.size() - 1 ? null : list.get(i + 1);
    }
    //中序遍历
    public void inOrder(TreeLinkNode root) {
        if (root != null) {
            inOrder(root.left);
            list.add(root);
            inOrder(root.right);
        }
    }
}
