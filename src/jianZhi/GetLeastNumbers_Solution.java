package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/1/30 - 19:25
 * @Description:最小的K个数 输入n个整数，找出其中最小的K个数
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
 */
public class GetLeastNumbers_Solution {
    /**
     * @Author: ZwZ
     * @Description: 快速排序，提取前k个数
     * @Param: [input, k] 
     * @return: java.util.ArrayList<java.lang.Integer> 
     * @Date: 2020/1/30-19:40
     */
    public ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        if (input == null || input.length == 0 || k > input.length || k == 0)
            return new ArrayList<>();
        quickSort(input, 0, input.length - 1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++)
            list.add(input[i]);
        return list;
    }

    /**
     * @Author: ZwZ
     * @Description: 构造一个大小为k的数组，每次将数组中的最大值与Input数组中的元素比较
     * 若input中待插入的值比此数组中最大值还要大，则一定不是要找的数
     * 若Input中待插入的值比此数组中最大值小，则可能是要找的数，则要存储此数组中，进行接下来的比较
     * 大小为k的数组用大根堆数据结构
     * @Param: [input, k] 
     * @return: java.util.ArrayList<java.lang.Integer> 
     * @Date: 2020/1/30-21:34
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k == 0)
            return list;
        int[] arr = new int[k + 1];//数组下标0的位置作为哨兵，不存储数据
        //初始化数组
        for (int i = 1; i < k + 1; i++)
            arr[i] = input[i - 1];
        buildMaxHeap(arr, k + 1);//构造大根堆
        for (int i = k; i < input.length; i++) {
            if (input[i] < arr[1]) {
                arr[1] = input[i];
                adjustDown(arr, 1, k + 1);//将改变了根节点的二叉树继续调整为大根堆
            }
        }
        for (int i = 1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * @Author: ZwZ
     * @Description: 构造大根堆 
     * @Param: [arr, length]  length:数组长度 作为是否跳出循环的条件
     * @return: void 
     * @Date: 2020/1/30-22:06
     */
    public void buildMaxHeap(int[] arr, int length) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;
        for (int i = (length - 1) / 2; i > 0; i--) {
            adjustDown(arr, i, arr.length);
        }
    }

    /**
     * @Author: ZwZ
     * @Description: 堆排序中对一个子二叉树进行堆排序 
     * @Param: [arr, k, length] 
     * @return:  
     * @Date: 2020/1/30-21:55
     */
    public void adjustDown(int[] arr, int k, int length) {
        arr[0] = arr[k];//哨兵
        for (int i = 2 * k; i <= length; i *= 2) {
            if (i < length - 1 && arr[i] < arr[i + 1])
                i++;//取k较大的子结点的下标
            if (i > length - 1 || arr[0] >= arr[i])
                break;
            else {
                arr[k] = arr[i];
                k = i; //向下筛选
            }
        }
        arr[k] = arr[0];
    }
    /**
     * @Author: ZwZ
     * @Description: 堆排序 
     * @Param: [arr, length] 
     * @return: void 
     * @Date: 2020/1/31-15:44
     */
    public void heapSort(int[] arr, int length) {
        buildMaxHeap(arr, length);
        int temp;
        for (int i = length - 1; i > 1; i--) {
            //交换堆顶元素
            temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;
            adjustDown(arr, 1, length - 1);//将剩余i-1个元素整理成堆
        }
    }
    //快速排序
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotPos = partition(arr, start, end);
            quickSort(arr, start, pivotPos - 1);
            quickSort(arr, pivotPos + 1, end);
        }
    }
    /** 
    * @Author: ZwZ
    * @Description: 一趟快速排序 
    * @Param: [array, left, right] 
    * @return: int 中轴值的最终位置
    * @Date: 2020/1/31-16:32
    */
    public static int partition(int[] arr, int left, int right) {
        if (arr == null)
            return 0;
        int pivot = arr[left];//中轴值
        //左右指针
        int temp;
        while (left < right) {
            while (arr[right] >= pivot && left < right)
                --right;
            arr[left] = arr[right];//将比中轴值小的元素移动到最左端
            while (arr[left] <= pivot && left < right)
                ++left;
            arr[right] = arr[left];//将比中轴值大的元素移动到最右端
        }
        arr[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8}; //下标为0的位置不存储元素
        GetLeastNumbers_Solution getLeastNumbers = new GetLeastNumbers_Solution();
        ArrayList<Integer> list = getLeastNumbers.GetLeastNumbers_Solution2(arr, 0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        /*getLeastNumbers.buildMaxHeap(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
    }
}
