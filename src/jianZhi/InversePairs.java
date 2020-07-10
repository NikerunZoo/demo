package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/2/5 - 14:05
 * @Description:数组中的逆序对 (较难)
 * 在数组中的两个数字，如果前面一个数字大于后面的数字 （数组中没有相同的数字）
 * 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P
 * 并将P对1000000007取模的结果输出 即输出P%1000000007
 */
public class InversePairs {
    /**
     * @Author: ZwZ
     * @Description：顺序遍历数组 
     * @Param: [array] 
     * @return: int 
     * @Date: 2020/2/5-14:55
     */
    public int InversePairs1(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j])
                    count++;
            }
        }
        return count % 1000000007;
    }

    /**
     * @Author: ZwZ
     * @Description:基于二路归并排序的思想计算数组中的逆序对 
     * @Param: [array] 
     * @return: int 
     * @Date: 2020/2/5-14:55
     */
    private int count = 0;

    public int InversePairs2(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        inverseMergeSort(array, 0, array.length - 1);
        return count;
    }

    private void inverseMergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) >> 1;
            inverseMergeSort(array, low, mid);
            inverseMergeSort(array, mid + 1, high);
            inverseMerge(array, low, mid, high);
        }
    }

    private void inverseMerge(int[] arr, int low, int mid, int high) {
        int[] arrTemp = new int[arr.length];
        //将arr中的元素复制到arrTemp中
        for (int i = low; i <= high; i++)
            arrTemp[i] = arr[i];
        int i, j, k;//i,j作为数组两部分的指针
        for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
            if (arrTemp[i] > arrTemp[j]) {
                count += (mid - i + 1);//两块合并的数据都是有序的
                arr[k] = arrTemp[j++];
            } else
                arr[k] = arrTemp[i++];
        }
        while (i <= mid)
            arr[k++] = arrTemp[i++];
        while (j <= high)
            arr[k++] = arrTemp[j++];
    }
    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        //结果：2519
        int[] arr = {364, 637, 341, 406, 747, 995, 234, 971, 571, 219, 993, 407, 416, 366, 315, 301, 601, 650, 418, 355, 460, 505, 360, 965, 516, 648, 727, 667, 465, 849, 455, 181, 486, 149, 588, 233, 144, 174, 557, 67, 746, 550, 474, 162, 268, 142, 463, 221, 882, 576, 604, 739, 288, 569, 256, 936, 275, 401, 497, 82, 935, 983, 583, 523, 697, 478, 147, 795, 380, 973, 958, 115, 773, 870, 259, 655, 446, 863, 735, 784, 3, 671, 433, 630, 425, 930, 64, 266, 235, 187, 284, 665, 874, 80, 45, 848, 38, 811, 267, 575};
        System.out.println(inversePairs.InversePairs2(arr));

    }
}
