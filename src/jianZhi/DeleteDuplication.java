package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/3/2 - 22:15
 * @Description:在一个排序的链表中，存在重复的结点
 * 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {
    public ListNode deleteDuplication(ListNode pHead) {
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode head = new ListNode(Integer.MIN_VALUE);//辅助头结点 防止头结点重复时被删除
        head.next = pHead;
        ListNode pre = head;
        ListNode node = head.next;
        while(node != null){
            if(node.next != null && node.value == node.next.value){
                while (node.next != null && node.value == node.next.value)
                    node = node.next;
                node = node.next;
                pre.next = node;
            }else{
                pre = node;
                node = node.next;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        DeleteDuplication delete = new DeleteDuplication();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        delete.deleteDuplication(null);
    }
}
