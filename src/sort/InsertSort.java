package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/3/22 - 15:22
 * @Description:插入排序
 * 假设一个数组长为N，前i个值是有序的
 * 把i+1的位置上的数按照大小顺序插入到已经有序的i个值中
 * 时间复杂度：
 * 当数组本身是从小到大有序：O(n)  因为每次内次for条件都不满足，所以不执行，只执行了外层for
 * 当数组本身是从大到小有序：O(n^2)
 * 当数据状态不同，算法复杂度不相同时，一律按照最差得情况对待，即插入排序得时间复杂度是O(N^2)
 * 插入排序虽然是O(N^2)但是N^2前面的系数较小，当数据量小的时候，体现不出来O(N^2)的劣势
 * 工程中的排序，当数据量小的时候，会选择使用插入排序而不是快排和堆排
 */
public class InsertSort {
    public void insertSort(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        //前i个值都是有序的
        for (int i = 1; i < arr.length; i++) {
            //将i+1位置上的数插入到前面有序数组中形成一个新的有序子数组 当arr[j] == arr[j+1]时不交换，所以可以做到稳定
            for (int j = i - 1; j >0 && arr[j] > arr[j+1] ; j--) {
                Swap.swap(arr, j, j + 1);
            }
        }
    }
}
