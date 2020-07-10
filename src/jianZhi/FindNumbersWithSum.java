package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/2/9 - 20:34
 * @Description:和为S的两个数字 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
 */
public class FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length <= 1 )
            return null;
        int left = 0;
        int right = array.length - 1;
        ArrayList<Integer>  list = new ArrayList<>();
        while (left < right) {
            if (array[left] + array[right] < sum) {
               left++;
            } else if (array[left] + array[right] > sum)
                right--;
            else {
                list.add(array[left]);
                list.add(array[right]);
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        FindNumbersWithSum find = new FindNumbersWithSum();
        int[] arr = {1,2,4,7,11,15};
        find.FindNumbersWithSum(arr,15);
    }
}
