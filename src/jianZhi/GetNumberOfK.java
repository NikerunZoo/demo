package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/2/6 - 10:16
 * @Description:数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数
 */
public class GetNumberOfK {
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0)
            return 0;
        int count = 0;
        for(int i = 0;i < array.length;i++){
            if(array[i] == k)
                count++;
        }
        return count;
    }
}
