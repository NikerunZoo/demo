package jianZhi;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author - ZwZ
 * @date - 2020/1/12 - 22:20
 * @Description:旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {
    /*链接：https://www.nowcoder.com/questionTerminal/9f3231a991af4f55b95579b44b7a01ba?answerType=1&f=discussion
    来源：牛客网
    //二分查找
    二分查找用于查找有序的数组中的值，题目所给数组在两段范围内有序，我们可以将给定数组分为两种情况：
        1.其实并没有旋转，例如 {1,2,3,4,5}，旋转后也是 {1,2,3,4,5}，这样可以直接使用二分查找
        2.如题所示，旋转了一部分，例如 {1,2,3,4,5}，旋转后为 {3,4,5,1,2}，需要限定特殊条件后使用二分查找
    当数组如情况 1，有个鲜明的特征，即数组最左边元素 < 数组最右边元素，这时我们直接返回首元素即可
    当数组如情况 2，此时有三种可能找到最小值：
        1.下标为 n+1 的值小于下标为 n 的值，则下标为 n+1 的值肯定是最小元素
        2.下标为 n 的值小于下标为 n-1 的值，则下标为 n 的值肯定是最小元素
    由于不断查找，数组查找范围内的值已经全为非降序（退化为情况1）
    再讨论每次二分查找时范围的变化，由于数组的情况 1 能直接找到最小值，需要变化范围的肯定是情况 2：
        1.当下标为 n 的值大于下标为 0 的值，从 0 到 n 这一段肯定是升序，由于是情况 2，最小值肯定在后半段
        2.当下标为 n 的值小于下标为 0 的值，从 0 到 n 这一段不是升序，最小值肯定在这一段*/
    public int minNumberInRotateArray1(int [] array) {
        if(array.length == 1)
            return array[0];
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = (left + right)/2;
            if(array[left] < array[right])
                return array[left];
            if(array[mid] > array[mid+1])
                return array[mid+1];
            if(array[mid] < array[mid-1])
                return array[mid];
            if(array[mid] > array[left]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return 0;
    }
    //暴力查找1（查找一个数组的最小值）
    public int minNumberInRotateArray2(int [] array) {
        if(array.length == 0)
            return 0;
        int min = 0;//最小值的下标
        for (int i = 0; i < array.length; i++) {
            if(array[i] < array[min]){
                min = i;
            }
        }
        return array[min];
    }
    //暴力查找2(当数组中的元素不再满足降序排列的时候，正是原数组旋转的地方，最小值就在这里)
    public int minNumberInRotateArray3(int [] array) {
        if(array.length == 0)
            return 0;
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i+1]){
                return array[i+1];
            }
        }
        return array[0];
    }
    //使用API
    public int minNumberInRotateArray4(int [] array) {
        if(array.length == 0)
            return 0;
        Arrays.sort(array);//对array数组从小到大排序（源码使用插入排序和快排）
        return array[0];
    }
    //使用API
    public int minNumberInRotateArray5(int [] array){
        if(array.length == 0)
            return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }
        return queue.poll();
    }
    public static void main(String[] args) {
        int[] array = {3,4,5,1,1,2,};
        MinNumberInRotateArray min = new MinNumberInRotateArray();
        System.out.println(min.minNumberInRotateArray1(array));
    }
}
