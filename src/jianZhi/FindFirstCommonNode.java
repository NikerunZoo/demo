package jianZhi;
import java.util.Stack;
/**
 * @author - ZwZ
 * @date - 2020/2/5 - 17:02
 * @Description:两个链表的第一个公共结点 输入两个链表，找出它们的第一个公共结点
 */
public class FindFirstCommonNode {
    /**
     * @Author: ZwZ
     * @Description:借助栈 
     * @Param: [pHead1, pHead2] 
     * @return: jianZhi.ListNode 
     * @Date: 2020/2/5-17:11
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        Stack<ListNode> list1Stack = new Stack<>();
        Stack<ListNode> list2Stack = new Stack<>();
        while (pHead1 != null) {
            list1Stack.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            list2Stack.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode node = null;
        while (list1Stack.size() != 0 && list2Stack.size() != 0) {
            if (list1Stack.peek() == list2Stack.peek()) {
                node = list1Stack.peek();
                list1Stack.pop();
                list2Stack.pop();
            }else
                break;
        }
        return node;
    }
    /** 
    * @Author: ZwZ
    * @Description:不借助栈
    * @Param: [pHead1, pHead2] 
    * @return: jianZhi.ListNode 
    * @Date: 2020/2/5-18:50
    */
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        int length1 = 0;
        int length2 = 0;
        ListNode temp1 = pHead1;
        ListNode temp2 = pHead2;
        //得到链表1的长度
        while(temp1 != null){
            length1++;
            temp1 = temp1.next;
        }
        //链表2的长度
        while(temp2 != null){
            length2++;
            temp2 = temp2.next;
        }
        //长度长的链表，遍历指针先走
        if(length1 < length2){
            int dVaule = length2 - length1;//差值
            //挪动指针
            while(dVaule != 0){
                pHead2 = pHead2.next;
                dVaule--;
            }
        }
        if(length1 > length2){
            int dVaule = length1 - length2;//差值
            //挪动指针
            while(dVaule != 0){
                pHead1 = pHead1.next;
                dVaule--;
            }
        }
        ListNode node = null;
        while(pHead1 != null && pHead2 != null){
            if(pHead1 != pHead2){
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
            }else{
                node = pHead1;
                break;
            }

        }
        return node;
    }
    public static void main(String[] args) {
        FindFirstCommonNode find = new FindFirstCommonNode();

        ListNode node2 = new ListNode(4);
        node2.next = new ListNode(5);

        ListNode nodeFirst = new ListNode(1);
        nodeFirst.next = new ListNode(2);
        nodeFirst.next.next = node2;

        ListNode nodeSecond = new ListNode(6);
        nodeSecond.next = new ListNode(7);
        nodeSecond.next.next = new ListNode(8);
        nodeSecond.next.next.next = node2;
        ListNode result = find.FindFirstCommonNode2(nodeFirst, nodeSecond);
        System.out.println(result.value);

    }
}
