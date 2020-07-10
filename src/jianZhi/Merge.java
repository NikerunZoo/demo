package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/20 - 11:05
 * @Description:合并两个排序的链表 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则
 */
public class Merge {
    public ListNode Merge(ListNode list1, ListNode list2) {
        //判空
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;
        ListNode list1_Node, list2_Node;
        ListNode head;//新链表头指针
        ListNode node = new ListNode(-1);//新链表中动态指针
        list1_Node = list1;
        list2_Node = list2;
        if (list1.value < list2.value) {
            head = list1;
        } else {
            head = list2;
        }
        while (list1_Node != null && list2_Node != null) {
            if (list1_Node.value < list2_Node.value) {
                node.next = list1_Node;
                list1_Node = list1_Node.next;//指针后移
            } else {
                node.next = list2_Node;
                list2_Node = list2_Node.next;
            }
            node = node.next;
        }
        while (list1_Node != null) {
            node.next = list1_Node;
            list1_Node = list1_Node.next;
            node = node.next;
        }
        while (list2_Node != null) {
            node.next = list2_Node;
            list2_Node = list2_Node.next;
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Merge merge = new Merge();

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(10);
        list1.next.next.next = new ListNode(20);

        ListNode list2 = new ListNode(4);
        list2.next = new ListNode(5);
        list2.next.next = new ListNode(6);
        list2.next.next.next = new ListNode(7);

        ListNode head = merge.Merge(list1, list2);

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}
