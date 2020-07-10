package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 22:43
 * @Description:快速排序：选择一个基准，通过一次遍历，确定基准的最终位置，其中基准左边的数都小于基准，右边的数都大于基准 然后再递归基准左边的元素和右边的元素
 * 相关算法：左神GetSmallSum和FlagOfHolland
 * 快排做不到稳定 因为partition过程做不到稳定
 */
public class QuickSort {
    public void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length < 2)
            return;
        if (low < high) {
            int[] pivotPos = partition(arr, low, high);//得到基准的位置
            quickSort(arr, low, pivotPos[0]);
            quickSort(arr, pivotPos[1], high);
        }
    }
    /** 
    * @Author: ZwZ
    * @Description: 此方法是在经典快速排序上进行的优化，经典快速排序每次只是确定一个元素的位置，然后再对左右两块子数组的元素进行快排
     * 但是，如果第一次的基准在数组中存在重复多次的值，那么就可以一次遍历之后，确定多个元素的位置
     * 灵感来源：左神FlagOfHolland
    * @Param: [arr, low, high] 
    * @return: int[] 
    * @Date: 2020/3/23-15:48
    */
    private int[] partition(int[] arr, int low, int high) {
        int left = low - 1;//下标left及其左边的元素，都是小于基准的
        int right = high + 1;//下标right及其右边的元素都是大于基准的
        Swap.swap(arr,low + (int)Math.random()*(high - low + 1),low); //使得快排变成随机快排  即pivot的下标是随机的 使得快排的效率不依赖于原始数据
        int pivot = arr[low];//基准
        int index = low;
        while (index < right) {
            if (arr[index] > pivot) {
                Swap.swap(arr, --right, index);
            } else if (arr[index] < pivot)
                Swap.swap(arr, ++left, index++);
            else
                index++;
        }
        return new int[]{left,right};
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = {25,84,21,25,15,27,68,35,20};
        sort.quickSort(arr,0,arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
