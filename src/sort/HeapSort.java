package sort;

import zuoPrimary.Swap;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 22:51
 * @Description:堆排序
 * 堆排序做不到稳定
 * 举例：4 4 4 5 5中，5向上跑的时候，和第二个4交换位置，那么第二个4就已经跑到了第三个4得后面
 */
public class HeapSort {
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        //建立大根堆(使用向上调整和向下调整都可以建立大根堆，由于向上调整代码简简洁，这里使用的是向上调整) 此过程的复杂度：O(log1+log2+log3...) 其收敛于O(N)
        for (int i = 0; i < arr.length; i++) {
            adjustUp(arr, i);
        }
        int heapSize = arr.length;
        Swap.swap(arr,0,--heapSize);//根节点和最后面的叶子结点换位，并且将堆的长度--，表示最后一个节点已经不再满足堆结构
        //通过不断地swap()和向下调整使得整个堆所在得数组变得有序
        while(heapSize > 0){
            adjustDown(arr,0,heapSize);//根节点变小了，要向下调整
            Swap.swap(arr,0,--heapSize);
        }
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:向上调整 (建立大根堆和当某一处的元素变大时 ， 使用向上调整)
     * @Param: [arr, index] 
     * @return: void 
     * @Date: 2020/3/23-16:39
     */
    private void adjustUp(int[] arr, int index) {
        //加入的元素比父节点的元素大
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;//指向父节点 即向上调整
        }
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:向下调整 当某一处元素变小时，要向下调整
     * @Param: [arr, index, heapSize] [待调整的数组，变小了的元素的数组下标，堆大小]
     * @return: void 
     * @Date: 2020/3/23-17:16
     */
    private void adjustDown(int[] arr, int index, int heapSize) {
        int left = (index * 2) + 1;//左侧子节点
        while (left < heapSize) {
            int max = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;//子节点中最大值的下标
            max = arr[max] < arr[index] ? index : max;//父节点和子节点中的最大值的下标
            //父节点虽然变小，但仍然比子节点都大
            if (max == index)
                break;
            Swap.swap(arr, max, index);
            index = max;//向下调整
            left = (index * 2) + 1;
        }
    }
}
