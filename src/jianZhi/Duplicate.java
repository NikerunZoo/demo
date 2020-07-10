package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/2/9 - 21:24
 * @Description:数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内
 * 数组中某些数字是重复的，但不知道有几个数字是重复的
 * 也不知道每个数字重复几次,请找出数组中任意一个重复的数字
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2
 */
public class Duplicate {
    /**
     * @Author: ZwZ
     * @Description:对数组进行排序 
     * @Param: [numbers, length, duplication] 
     * @return: boolean 
     * @Date: 2020/2/9-21:32
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0)
            return false;
        quickSort(numbers, 0, length - 1);
        for (int i = 0; i < length; i++) {
            System.out.print(numbers[i]);
        }
        for (int i = 0; i < length; i++) {
            if (i!= length - 1 && numbers[i] != numbers[i + 1])
                continue;
            else {
                if(i == length - 1)
                    return false;
                duplication[0] = numbers[i];
                break;
            }
        }
        return true;
    }

    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pivotPosition = partition(arr, low, high);
            quickSort(arr, low, pivotPosition - 1);
            quickSort(arr, pivotPosition + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pviot = arr[low];
        while (low < high) {
            while (arr[high] >= pviot && low < high)
                high--;
            arr[low] = arr[high];
            while (arr[low] <= pviot && low < high)
                low++;
            arr[high] = arr[low];
        }
        arr[low] = pviot;
        return low;
    }

    public static void main(String[] args) {
        Duplicate duplicate = new Duplicate();
        int[] arr = {2, 1, 3, 1, 4};
        int[] result = new int[1];
        System.out.println(duplicate.duplicate(arr, 5, result));
        System.out.println(result[0]);
    }
}
