package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/19 - 14:02
 * @Description:链表中倒数第k个结点
 * 链表中倒数第k个结点 输入一个链表，输出该链表中倒数第k个结点
 */
public class FindKthToTail {
    //两次遍历  注意代码的鲁棒性
    public ListNode FindKthToTail1(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        ListNode node = head;
        int count = 1;
        //计算节点数量
        while (node.next != null){
            node = node.next;
            count++;
        }
        if(k > count)
            return null;
        for (int i = 0; i <count - k; i++) {
            head = head.next;
        }
        return head;
    }
    //借助两个指针 一次遍历
    public ListNode FindKthToTail2(ListNode head, int k) {
        ListNode front ,rear;
        front = rear = head;
        if(k  <= 0)
            return null;
        //rear向前走k步
        for (int i = 0; i <k-1 ; i++) {
            if(rear == null)
                return null;
            rear = rear.next;
        }
        //链表长度小于k
        if(rear == null)
            return null;
        //rear和front一起走
        while(rear.next != null){
            rear = rear.next;
            front =  front.next;
        }
        return front;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next =  new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        FindKthToTail findKthToTail = new FindKthToTail();
        System.out.println(findKthToTail.FindKthToTail1(head,1).value);
    }
}
