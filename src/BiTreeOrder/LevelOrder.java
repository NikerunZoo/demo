package BiTreeOrder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/2/14 - 21:03
 * @Description:层次遍历
 */
public class LevelOrder {
    public void levelOrder(TreeNode root){
        if(root == null)
            return;
        Queue<TreeNode> queue = new LinkedList();//队列
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node =  queue.poll();//删除队头
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
    }
}
