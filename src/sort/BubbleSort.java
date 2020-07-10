package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 16:12
 * @Description:冒泡排序 (由于时间复杂度高 ， 这种排序算法几乎没有人使用在工程中)
 * 第一轮：
 * 0----1位置比较，并判断是否交换
 * 1----2位置比较，并判断是否交换
 * 2----3位置比较，并判断是否交换
 * ...
 * n-2--n-1位置比较，并判断是否交换
 * 第二轮(此时n-1位置上的数已经确定)：
 * 0----1位置比较，并判断是否交换
 * 1----2位置比较，并判断是否交换
 * 2----3位置比较，并判断是否交换
 * ...
 * n-3--n-2位置比较，并判断是否交换
 * ........
 * 时间复杂度和数据本身状况无关，恒为：O(n^2)
 *
 * 可以做到稳定：当两个值相等时，用后面那个再去往下冒，而不进行交换
 */
public class BubbleSort {
    /**
     *  
     *
     * @Author: ZwZ
     * @Description:最原始的冒泡排序
     * @Param: [arr] 
     * @return: void 
     * @Date: 2020/2/18-16:19
     */
    public void bubbleSort1(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        //外层for控制每次遍历的终止点
        for (int end = arr.length - 1; end > 0; end--) {
            //内层for用于比较和交换
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1])
                    Swap.swap(arr, i, i + 1);
            }
        }
    }

    /**
     * @Author: ZwZ
     * @Description:优化后冒泡排序 
     * @Param: [arr] 
     * @return: void 
     * @Date: 2020/2/18-16:19
     */
    public void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int end = arr.length - 1; end > 0; end--) {
            boolean flag = true;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    Swap.swap(arr, i, i + 1);
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] arr = {3, 5, 4, 2, 1};
        sort.bubbleSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
