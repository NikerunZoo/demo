package jianZhi;

import java.util.Stack;
/**
 * @author - ZwZ
 * @date - 2020/1/21 - 15:37
 * @Description:包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
 */
public class Stack {
    java.util.Stack<Integer> stack1 = new java.util.Stack<>();
    java.util.Stack<Integer> stack2 = new java.util.Stack<>();//用于存储每加入一个元素之后stack1中的最小值

    public void push(int node) {
        if (stack2.empty())
            stack2.push(node);
        else {
            int min = stack2.peek();
            if (node <= min)
                stack2.push(node);
        }
        stack1.push(node);
    }

    public void pop() {
        if (!stack1.empty()) {
            int top = stack1.pop();
            int min = stack2.peek();
            if (top == min)
                stack2.pop();
        }
    }

    public int top() {
        if (!stack1.empty())
            return stack1.peek();
        else
            return 0;
    }

    public int min() {
        if (!stack2.empty())
            return stack2.peek();
        else
            return 0;
    }

}
