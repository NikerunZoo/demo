package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/3/2 - 21:28
 * @Description:圆圈中最后剩下的数字 0, 1, 2, , , n-1排成一个圆圈，从0开始每次从圆圈中删除第m个数字
 * 求圆圈中最后一个数字
 */
public class LastRemaining {
    /**
     *  
     *
     * @Author: ZwZ
     * @Description:用环形链表模拟圆圈，每次删除第m个元素 
     * @Param: [n, m] 
     * @return: int 
     * @Date: 2020/3/2-21:55
     */
    public int LastRemaining1(int n, int m) {
        if (n == 0)
            return -1;
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head;
        int k = 0;
        while (node.next != node) {
            if (++k == m) {
                node.next = node.next.next;//删除
                k = 0;
            } else
                node = node.next;
        }
        return node.value;
    }
}
