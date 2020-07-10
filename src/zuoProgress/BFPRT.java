package zuoProgress;

import zuoPrimary.Swap;

import java.util.Arrays;

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
 *
 * @author - ZwZ
 * @date - 2020/4/3 - 0:11
 * @Description:BFPRT算法
 * 求一个无序数组中最大/小的第K个数
 * 思路1：使用荷兰国旗思想(快速排序的partition)，每次得到确定一个数的位置m
 *  m < K,继续重复操作右边
 *  m > K,继续重复操作左边
 * 思路2：BFPRT算法
 * 只是和快速排序的partition有一处差别：选择pivot的方式不是随机
 * 首先将原数组分成 5 个一组，每组内进行排序，组间不排序
 * 然后将每组的中位数取出再次进行上述操作,然后取出中位数
 * 将这个中位数当作pivot进行 partition 操作
 *
 * 应用：求一个无序数组中最大/小的K个数
 * 先用BFPRT算法求出第K大/小的数，然后再遍历一遍原数组
 * 或者使用堆排序
 */
public class BFPRT {
    public static int getMinKthByBFPRT(int[] arr,int k) {
        int[] copyArr = new int[arr.length];
        copyArr = copyArray(arr);
        return bfprt(copyArr,0,copyArr.length - 1,k - 1);
    }

    private static int[] copyArray(int[] arr) {
        int[] tmp = new int[arr.length];
        for(int i = 0;i != arr.length;i++)
            tmp[i] = arr[i];
        return tmp;
    }

    private static int bfprt(int[] arr,int begin,int end,int i) {//begin到end范围内求第i小的数
        if(begin == end)
            return arr[begin];
        int pivot = medianOfMedians(arr,begin,end);//中位数作为划分值
        int[] pivotRange = partition(arr,begin,end,pivot);//进行划分，返回等于区域
        if(i >= pivotRange[0] && i <= pivotRange[1])
            return arr[i];
        else if(i < pivotRange[0])
            return bfprt(arr,begin,pivotRange[0] - 1,i);
        else
            return bfprt(arr,pivotRange[1] + 1,end,i);
    }

    private static int medianOfMedians(int[] arr,int begin,int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for(int i = 0; i < mArr.length;i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr,beginI,Math.min(end,endI));
        }
        return bfprt(mArr,0,mArr.length - 1,mArr.length / 2);
    }

    private static int getMedian(int[] arr,int begin,int end) {
        Arrays.sort(arr,begin,end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    private static int[] partition(int[] arr, int low, int high,int pivot) {
        int left = low - 1;//下标left及其左边的元素，都是小于基准的
        int right = high + 1;//下标right及其右边的元素都是大于基准的
        int index = low;
        while (index < right) {
            if (arr[index] > pivot) {
                Swap.swap(arr, --right, index);
            } else if (arr[index] < pivot)
                Swap.swap(arr, ++left, index++);
            else
                index++;
        }
        return new int[]{left,right};
    }
}
