package BiTreeOrder;

/**
 * @author - zwz
 * @date - 20-4-10-下午7:00
 * @Description:Morris遍历
 * 经典的先 中 后序遍历无论是递归版本还是非递归版本，空间复杂度都是O(h) h是二叉树的高度
 * 因为都是使用栈去记录之前走过的位置，以用来下次可以直接回到那个位置
 * 递归使用的是系统栈，非递归使用的是自己创造的栈
 * Morris遍历借助一颗二叉树中大量的空指针，来实现空间复杂度O(1)且时间复杂度O(N)
 * 1）如果cur没有左孩子，cur向右移动
 * 2）如果cur有左孩子，找到cur左子树上最右节点，记为mostRight
 *      2.1）如果mostRight的right指针指向null,则让其指向cur,然后cur = cur.left
 *      2.2）如果mostRight的right指向了cur,让其指回空，cur = cur.right
 * Morris遍历中，如果某节点有左子树，则会整个过程会来到这个节点两次，如果它没有那么只会来到一次
 */
public class Morris {
    public static class TreeNode {
        private int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:使用morris遍历来完成中序遍历
     * 使用morris遍历完成先中后遍历不同之处仅在于输出每个节点的时机不同
     * @Date: 下午10:09 20-4-10
     * @Param: [head]
     * @return: void
     **/
    public static void morrisIn(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            //当前节点有左子树
            if (mostRight != null) {
                //寻找当前节点左子树的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //第一次来到mostRight
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {//mostRight.right == cur即第二次来到mostRight
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");//输出
            cur = cur.right;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:Morris遍历之先序遍历
     * @Date: 下午10:24 20-4-10
     * @Param: [head]
     * @return: void
     **/
    public static void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");//输出
                    cur = cur.left;
                    continue;
                } else {//mostRight.right == cur
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;//回
        }
    }
    /*
     * @Author: ZwZ
     * @Description: Morris遍历之后序遍历
     * 先序遍历是在第一次到达某节点的时候打印输出，中序遍历是在第二次到达某节点时候即从左子树返回到当前节点时打印输出
     * 而后序遍历要在第三次来到某节点即从右子树返回到当前节点时打印输出，但是morris遍历每个节点最多访问两次，所以比先序和后序较为复杂：
     * 对于一个节点，先处理左子树，但并不是把整棵左子树都处理完，而是留下该左子树的右边界
     * 当回到该节点时，才逆序打印整个右边界，这样左子树就处理完了
     * 这时回到该节点但并不处理该点，而是直接进入右子树处理，待到右子树顺着最右节点回到上层父节点时
     * 该节点才做为上层父节点的左子树右边界被打印
     * 参考博客：https://bobjin.com/blog/view/bb27ecb42c56e91313fb7460ab530bcf.html
     * @Date: 下午10:52 20-4-10
     * @Param: [head]
     * @return: void
     **/
    public static void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);//第二次来到:逆序打印其左子树的右边界
                }
            }
            cur = cur.right;
        }
        printEdge(head);//遍历全部完成后逆序打印整棵树的右边界
    }
    /*
     * @Author: ZwZ
     * @Description:逆序打印某棵树的右边界
     * @Date: 下午10:50 20-4-10
     * @Param: [head]
     * @return: void
     **/
    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);//打印完成后再翻转一次，将树还原
    }
    /*
     * @Author: ZwZ
     * @Description:将右边界逆序 右边界一条线可以看成一个单链表，而将右边界逆序就是将此单链表翻转
     * @Date: 下午11:27 20-4-10
     * @Param: [from]
     * @return: BiTreeOrder.Morris.TreeNode
     **/
    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
       /* morrisIn(head);
        System.out.println();
        morrisPre(head);
        System.out.println();*/
        morrisPos(head);
    }

}
