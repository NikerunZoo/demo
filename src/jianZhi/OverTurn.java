package jianZhi;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/2/5 - 19:48
 * @Description:翻转链表
 */
public class OverTurn {
    public static ListNode overTurn(String[] str) {
        if (str == null || str.length == 0)
            return null;
        ListNode pHead = new ListNode(Integer.parseInt(str[0]));//链表头节点
        ListNode temp = pHead;
        //通过数组中 的数据建立对应的链表
        for (int i = 1; i < str.length; i++) {
            temp.next = new ListNode(Integer.parseInt(str[i]));
            temp = temp.next;
        }
        temp = pHead;
        Stack<ListNode> stack = new Stack();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        temp = pHead;
        while (stack.peek() != pHead) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        temp.next = null;
        return pHead;
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(",");//剔除控制台打印字符串中的","
        ListNode head = overTurn(strings);
        while (head != null) {
            if(head.next==null){
                System.out.print(head.value);
            }else
                System.out.print(head.value+",");
            head=head.next;
        }
    }
}
