package jianZhi;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/1/12 - 11:39
 * @Description:用两个栈实现一个队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。
 */
/*
链接：https://www.nowcoder.com/questionTerminal/54275ddae22f475981afa2244dd448c6?answerType=1&f=discussion
来源：牛客网
1.当插入时，直接插入 stack1
2.当弹出时，当 stack2 不为空，弹出 stack2 栈顶元素，如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素*/
public class StackRealizeQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    //将指定元素插入到队尾
    public void push(int node) {
        stack1.push(node);
    }
    //将队头元素出队
    /*public int pop() {//错误代码  没有考虑当stack2不为空的情况 即stack1-->stack2之后，stack1又进入了新元素这种情况
        while(!stack1.empty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }*/
    public int pop(){
        if (!stack2.empty()){
            return stack2.pop();
        }else{
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}
