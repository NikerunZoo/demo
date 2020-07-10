package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/29 - 22:05
 * @Description:数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2
 * 如果不存在则输出0
 */
public class MoreThanHalfNum_Solution {
    /**
     * @Author: ZwZ
     * @Description: 对元素排序 中间位置处的数字则为出现次数超过数组长度一半的数字 
     * @Param: [array] 
     * @return: int 
     * @Date: 2020/1/29-22:44
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null)
            return 0;
        int len = array.length;
        if (len == 1 || (len == 2 && array[0] == array[1]))
            return array[0];
        int left = 0;
        int right = len - 1;
        int middle = (len >> 1);//右移代替/2操作 效率高
        int index = partition(array, left, right);
        while (index != middle) {
            if (index < middle) {
                left = index + 1;
                index = partition(array, left, right);
            }
            else{
                right = index - 1;
                index = partition(array, 0, right);
            }
        }
        int result = array[middle];
        /*
         * 若数组中存在此元素，则排序后此元素一定在下标middle处
         * 若数组中不存在此元素，则排序后下标middle处的元素并不是所要找的元素
         * 所以要进行一次判断
         * */
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result)
                count++;
        }
        if (count <= middle)
            return 0;
        return result;
    }
    /**
     * @Author: ZwZ
     * @Description: 一趟快速排序 
     * @Param: [array] 
     * @return: int 中轴值的最终位置
     * @Date: 2020/1/29-22:46
     */
    public static int partition(int[] array, int left, int right) {
        if (array == null)
            return 0;
        int pivot = array[left];//中轴值
        //左右指针
        while (left < right) {
            while (array[right] >= pivot && left < right)
                --right;
            array[left] = array[right];//将比中轴值小的元素移动到最左端
            while (array[left] <= pivot && left < right)
                ++left;
            array[right] = array[left];//将比中轴值大的元素移动到最右端
        }
        array[left] = pivot;
        return left;
    }
    public static void main(String[] args) {
        MoreThanHalfNum_Solution moreThanHalfNum = new MoreThanHalfNum_Solution();
        int[] arr = {1,2,3,2,4,2,5,2,3};
        System.out.println(moreThanHalfNum.MoreThanHalfNum_Solution(arr));
    }
}