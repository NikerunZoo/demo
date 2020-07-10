package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/31 - 17:14
 * @Description:连续子数组的最大和
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学
 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和
 * 当向量全为正数的时候,问题很好解决
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        int len = array.length;//数组长度(题目中告诉子向量即子数组的长度至少是1，所以无需考虑array == null || array.length == 0的情况)
        if(len == 1)
            return array[0];
        int maxSum = Integer.MIN_VALUE;//最大的子数组和 不可以使用0 / -1 等等值去给maxSum赋初值
        int sum = 0;
        for (int i = 0; i < len; i++) {
            //说明之前加的数的总和为负，无法为总和做积极的贡献，于是舍弃并从下标i元素开始再次记录
            if(sum <= 0)
                sum = array[i];
            else
                sum += array[i];
            if(sum > maxSum)
                maxSum = sum;
        }
        return maxSum;
    }
    public static void main(String[] args) {
        int[] array = {-1,-3,-2,-4,-15};
        FindGreatestSumOfSubArray find = new FindGreatestSumOfSubArray();
        System.out.println(find.FindGreatestSumOfSubArray(array));
    }
}
