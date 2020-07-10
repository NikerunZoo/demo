package sort;

/**
 * @author - ZwZ
 * @date - 2020/2/18 - 19:38
 * @Description:2路归并排序 思想：递归 左边排好序，右边排好序，然后再借助辅助数组把左右整体排好序 结束
 * 可以使用左神的计算递归的时间复杂度的公式得出2路归并排序的时间复杂度是T(N) = 2T(N/2)+O(N) 得出：O(N*logN)
 * 2路归并排序是可以做到稳定
 * 举例：
 * 33567   12334 合并时，当左边和右边有相等元素的时候，先拷贝左边至arr再拷贝右边到arr
 */
public class MergeSort {
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        sort(arr, 0, arr.length - 1);
    }
    private void sort(int[] arr, int low, int high) {
        //递归结束条件 即范围上只有一个数
        if (low == high)
            return;
        int mid = (low + high) / 2;
        sort(arr, low, mid);//左边排好序 T(N/2)
        sort(arr, mid + 1, high);//右边排好序 T(N/2)
        merge(arr, low, mid, high);//使得左右整体排好序 T(N)
    }
    private void merge(int[] arr, int low, int mid, int high) {
        int[] arrTemp = new int[high - low + 1];//辅助数组
        int p1 = low;
        int p2 = mid + 1;
        int i = 0;
        //将排好序的数存入临时数组
        while (p1 <= mid && p2 <= high) {
            arrTemp[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid)
            arrTemp[i++] = arr[p1++];
        while (p2 <= high)
            arrTemp[i++] = arr[p2++];
        //复制
        for (int j = 0; j < arrTemp.length; j++) {
            arr[low++] = arrTemp[j];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {1,3,4,2,5};
        mergeSort.mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
