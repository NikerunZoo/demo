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
 *
 * @author - ZwZ
 * @date - 2020/4/3 - 22:45
 * @Description:生成窗口最大值敷组[题目]
 * 有一个整型数组arr和-个大小为w的窗口从数组的左边滑到最右边，窗口每次向右边滑一个位置
 * 例如，数组为[4,3.5.4.3.3.6,7],窗口大小为3时:
 * [435]43367  窗口中最大值为5
 * 4[354]3367  窗口中最大值为5
 * 43[543]367  窗口中最大值为5
 * 435[433]67  窗口中最大值为4
 * 4354[336]7  窗口中最大值为6
 * 43543[367]  窗口中最大值为7
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。
 * 输入:整型数组arr,窗口大小为w。
 * 输出:一个长度为n-w+1的数组res, res[i]表示每一种窗口状态下的最大值，
 * 以上述为例，结果应该返回(5.5.5.4.6.7).
 * 思路：
 * 在WindowMax的基础上进行定制
 */
public class SlidingWindowMaxArray {
    /** 
    * @Author: ZwZ
    * @Description:对WindowMax中的思想的定制
    * @Param: [arr, W] 
    * @return: int[] 
    * @Date: 2020/4/6-12:21
    */
    public int[] getMaxWindow(int[] arr, int W){
        if(arr == null || W < 1 || W > arr.length)
            return null;
        LinkedList<Integer> qMax = new LinkedList<>();//双端队列 它还是存储的窗口每个状态下的最大值的下标
        int index = 0;
        int[] res = new int[arr.length - W + 1];
        for (int i = 0; i < arr.length; i++) {
            while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i])
                qMax.pollLast();
            qMax.addLast(i);
            //本题目中窗口大小固定，当此if成立时，说明qMax的头结点此时已经“过期”即窗口已经滑过它的位置 就好比left--的过程
            if(i - W == qMax.peekFirst())
                qMax.pollFirst();
            if(i > W - 1)
                res[index++] = arr[qMax.peekFirst()];
        }
        return res;
    }
}