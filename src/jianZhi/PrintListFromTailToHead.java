package jianZhi;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/1/10 - 23:34
 * @Description:从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
class ListNode {
    int value;
    ListNode next = null;

    ListNode(int value) {
        this.value = value;
    }
}

public class PrintListFromTailToHead {
    ArrayList<Integer> list1 = new ArrayList<>();

    //借助栈 (从前到后遍历，从后到前输出)
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        Stack<Integer> stack = new Stack();
        ArrayList<Integer> list = new ArrayList();
        while (listNode != null) {
            stack.push(listNode.value);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            Integer integer = stack.pop();
            list.add(integer);
        }
        return list;
    }

    //递归，即使用系统栈
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead2(listNode.next);
            list1.add(listNode.value);
        }
        return list1;
    }

    //使用API的特性
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(0, listNode.value);
            listNode = listNode.next;
        }
        return list;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        PrintListFromTailToHead pLFT = new PrintListFromTailToHead();
        //测试
        ArrayList list = pLFT.printListFromTailToHead3(listNode);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
