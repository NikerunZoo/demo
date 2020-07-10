package zuoProgress.chuangKouMaxAndMin;

import java.util.LinkedList;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //         .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 * @author - ZwZ
 * @date - 2020/4/6 - 12:49
 * @Description:最大值减去最小值小于或等于num的子数组数量
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况:max(arr[i,.j])-min(arr[i.,j]) <= num
 * max(arr[i.. j])表示子数组arr[i..j]中的最大值，min(arr[i.. j])表示子数组arr [i，j]中的最小值
 * [要求]:
 * 如果数组长度为N，请实现时间复杂度为0(N)的解法，
 */
public class AllLessNumSubArray {
    /** 
    * @Author: ZwZ
    * @Description:暴力
     * 1.找出来所有的子数组O(N^2)
     * 2.遍历子数组判断是否满足最大值减去最小值小于或等于num
     * 总体时间复杂度：O(N^3) 
    * @Param: [arr, num] 
    * @return: int 
    * @Date: 2020/4/7-11:22
    */
    public int getSum1(int[] arr, int num) {
        if (arr == null || arr.length == 0)
            return 0;
        int res = 0;
        //得到所有的子数组并验证
        for (int start = 0; start < arr.length; start++) {
            for (int end = 0; end < arr.length; end++) {
                if(isValid(arr,start,end,num))
                    res++;
            }
        }
        return res;
    }
    /** 
    * @Author: ZwZ
    * @Description:借用窗口最大值最小值思想
     * 思路：
     *  假如一个子数组达标，那么这个子数组中的所有子数组都达标，因为它的子数组内的max一定更小，min一定更大，导致max - min只会比num更小
     *  假如一个子数组不达标，那么这个子数组的右侧向外扩之后，也一定不达标，因为向外扩之后max只会更大，min只会更小，所以max - min只会更大
     *  使用窗口，left从0出发，right从left出发++,当遇到不达标的时候，以left为最左的所有子数组满足条件的子数组都可以确定其数量
     *  然后left++，right继续从Left出发++重复上述步骤，直到left==arr.length-1
     * 时间复杂度：O(N)
    * @Param: [arr, num] 
    * @return: int 
    * @Date: 2020/4/7-12:11
    */
    public int getSum2(int[] arr,int num){
        if(arr == null || arr.length == 0)
            return 0;
        int left = 0;
        int right = 0;
        int res = 0;
        LinkedList<Integer> maxList = new LinkedList<>();//存储最大值的双端队列 队列内容从大到小
        LinkedList<Integer> minList = new LinkedList<>();//存储最小值的双端队列 队列内容从小到大
        while(left < arr.length){
            while(right < arr.length){
                //更新存储最小值的双端队列
                while(!minList.isEmpty() && arr[minList.peekLast()] >= arr[right])
                    minList.pollLast();
                minList.addLast(right);
                //更新存储最大值的双端队列
                while(!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[right])
                    maxList.pollLast();
                maxList.addLast(right);
                //本子数组不达标，right++后的子数组一定不达标，直接break此while
                if(maxList.peekFirst() - minList.peekFirst() > num)
                    break;
                right++;
            }
            //当跳出内层while时，即将left++进入下一个外层while 在进入下一个外层while时，要将双端对列更新，即防止下一个while循环进入后队列中存储的数据过期
            if(minList.peekFirst() == left)
                minList.pollFirst();
            if(maxList.peekFirst() == left)
                maxList.pollFirst();
            res += right - left;//满足条件的子数组中以left开头的所有的子数组都是满足条件的，一共有right - left个
            left++;
        }
        return res;
    }
    /**
     * @Author: ZwZ
     * @Description:验证某子数组是否满足最大值减去最小值小于或等于num 
     * @Param: [arr, start, end, num] 
     * @return: boolean 
     * @Date: 2020/4/7-11:18
     */
    private boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        return max - min <= num;
    }
}
