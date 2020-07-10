package jianZhi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/1/25 - 20:31
 * @Description:复杂链表的复制 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）
 * 返回结果为复制后复杂链表的head
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Clone {
    /*** 
     * @Author: ZwZ
     * @Description: 暴力赋值新链表中每个节点的random
     * @Param: [pHead] 
     * @return: jianZhi.RandomListNode 
     * @Date: 2020/1/25-21:08
     */
    public RandomListNode Clone1(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode newHead = new RandomListNode(pHead.label);
        RandomListNode newListPoint = newHead;//新链表临时指针
        RandomListNode oldListPoint = pHead.next;//旧链表临时指针
        int length = 0;//链表长度
        //复制原链表(用next维持的链表部分)
        while (oldListPoint != null) {
            //newListPoint.next = oldListPoint.next; 只将引用指向原链表是不对的
            newListPoint.next = new RandomListNode(oldListPoint.label);
            oldListPoint = oldListPoint.next;
            newListPoint = newListPoint.next;
            length++;
        }
        //临时指针复原位
        newListPoint = newHead;
        oldListPoint = pHead;
        RandomListNode oldTemp = pHead;
        RandomListNode newTemp = newHead;
        int randomPosition = 0;

        //更新新链表中每个节点的random
        while (oldListPoint != null) {
            if (oldListPoint.random != null) {
                //寻找random
                for (int i = 0; i <= length; i++) {
                    if (oldListPoint.random == oldTemp) {
                        randomPosition = i;//random距离链表头的长度
                        break;
                    }
                    oldTemp = oldTemp.next;
                }
                oldTemp = pHead;//复原
                //新链表中寻找对应节点的random
                for (int i = 0; i < randomPosition; i++) {
                    newTemp = newTemp.next;
                }
                newListPoint.random = newTemp;
                newTemp = newHead;
            }
            newListPoint = newListPoint.next;
            oldListPoint = oldListPoint.next;
        }
        return newHead;
    }

    /*** 
     * @Author: ZwZ
     * @Description: 使用哈希表赋值新链表中每个节点的random 空间换时间
     * @Param: [pHead] 
     * @return: jianZhi.RandomListNode 
     * @Date: 2020/1/26-20:21
     */
    public RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode newHead = new RandomListNode(pHead.label);
        RandomListNode newListPoint = newHead;//新链表临时指针
        RandomListNode oldListPoint = pHead.next;//旧链表临时指针
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        map.put(pHead, newHead);
        //复制原链表 (用next维持的链表部分)
        while (oldListPoint != null) {
            //newListPoint.next = oldListPoint.next; 只将引用指向原链表是不对的
            newListPoint.next = new RandomListNode(oldListPoint.label);
            map.put(oldListPoint, newListPoint.next);//记录旧节点和新节点之间对应关系
            oldListPoint = oldListPoint.next;
            newListPoint = newListPoint.next;
        }
        //指针复原
        oldListPoint = pHead;
        //更新新链表中每个节点的random
        while (oldListPoint != null) {
            map.get(oldListPoint).random = map.get(oldListPoint.random);
            oldListPoint = oldListPoint.next;
        }
        return newHead;
    }

    /*** 
     * @Author: ZwZ
     * @Description: 在不使用辅助空间的情况下实现o(n) 《剑指OFFER》
     * 方法2得到一个节点的拷贝节点的方式是查hash表
     * 而这个方法中，某节点的拷贝节点一定是这个节点的下一个节点 使用这种结构将hash表省去从而节省空间
     * @Param: [pHead] 
     * @return: jianZhi.RandomListNode 
     * @Date: 2020/1/26-21:04
     */
    public RandomListNode Clone3(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode currentNode = pHead;
        //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
        while (currentNode != null) {
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        currentNode = pHead;
        //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        while (currentNode != null) {
            currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
            currentNode = currentNode.next.next;
        }
        //3、拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            currentNode = currentNode.next;
        }
        return pCloneHead;
    }

    public static void main(String[] args) {
        Clone clone = new Clone();
        RandomListNode node = new RandomListNode(1);
        node.next = new RandomListNode(2);
        node.next.next = new RandomListNode(3);
        node.next.next.next = new RandomListNode(4);
        node.next.next.next.next = new RandomListNode(5);
        node.random = node.next.next;
        node.next.random = node.next.next.next.next;
        RandomListNode newList = clone.Clone3(node);
        while (newList != null) {
            System.out.println(newList.label);
            if (newList.random != null)
                System.out.println(newList.random.label);
            else
                System.out.println("null");
            System.out.println("-----------------");
            newList = newList.next;

        }
    }
}
