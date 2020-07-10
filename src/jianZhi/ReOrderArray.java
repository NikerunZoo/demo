package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/19 - 10:49
 * @Description:调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ReOrderArray {
    //不保证基数和基数，偶数和偶数之间的相对位置不变
    public void reOrderArray1(int[] array) {
        int front = 0, rear = array.length - 1;//指针
        int temp = 0;
        while (front < rear) {
            //array[front]是奇数
            if ((array[front] & 1) == 1) {
                ++front;
            }
            //array[front]是偶数
            if ((array[front] & 1) == 0) {
                //交换
                temp = array[rear];
                array[rear] = array[front];
                array[front] = temp;
                --rear;
            }
        }
    }
    /*链接：https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?answerType=1&f=discussion
    来源：牛客网
    i++往前走碰到偶数停下来，j = i+1
    若 a[j]为偶数，j++前进，直到碰到奇数
    a[j]对应的奇数插到a[i]位置，j经过的j-i个偶数依次后移
    如果j==len-1时还没碰到奇数，证明i和j之间都为偶数了，完成整个移动*/
    public void reOrderArray2(int[] array) {
        int len = array.length;
        if (len <= 1)
            return;
        int front = 0, rear = front + 1;//指针
        int temp;
        while (front < len) {
            while ((array[front] & 1) == 1 && front < len - 1) {
                front++;
            }
            rear = front + 1;
            while ((array[rear] & 1) == 0 && rear >= 0) {
                if (rear == len - 1)
                    return;
                rear++;
            }
            temp = array[rear];
            //后移
            for (int i = rear; i > front; i--) {
                array[i] = array[i - 1];
            }
            array[front] = temp;//插入
            front++;
        }
    }
    //借助两个辅助数组分别存放偶数和奇数 然后一次遍历原数组，提出每个数加入到相对应的辅助数组
    public void reOrderArray3(int[] array) {
    }
    public static void main(String[] args) throws Throwable {
        ReOrderArray reOrderArray = new ReOrderArray();
        reOrderArray.finalize();
        int[] array = {1, 2, 3, 4, 5,6,7,8,9,10,11,12,13};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
