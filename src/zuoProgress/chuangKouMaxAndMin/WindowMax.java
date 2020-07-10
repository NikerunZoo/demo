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
 * @date - 2020/4/6 - 11:55
 * @Description:求窗口中的最大值做到O(1)
 * 窗口使用数组来模拟，left指向窗口中数据最左边的位置，right指向窗口中最右边的位置
 * 当窗口中有数据进入时，right++,当窗口中有数据删除时：left++，并且left和right都只能增加，不可以回退
 */
public class WindowMax {
    LinkedList<Integer> list = new LinkedList<>();//双端队列 逻辑上记录窗口中最大值的位置的下标以及数值 (实际只需要记录最大值在arr中的下标即可) 其中的数据从大到小排列
    int[] arr = new int[256];//大小为256的窗口
    int left = -1;
    int right = -1;
    /** 
    * @Author: ZwZ
    * @Description:添加数据进入窗口  这里不考虑窗口的假溢出
     * 添加数据时，当此时双端链表中末尾的数据比n小或者相等，则弹出
     * 当其比n大时，将n加入到队尾
     * 为什么n小于前一个数的话要保留：因为当前边的那个数删除之后，此时的最大值就会改变成它后面的数，所以是要保留的
    * @Param: [n] 
    * @return: void 
    * @Date: 2020/4/6-12:01
    */
    public void add(int n){
        while (!list.isEmpty() && arr[list.peekLast()] < n){
            list.pollLast();
        }
        list.addLast(n);
        right++;
    }
    /** 
    * @Author: ZwZ
    * @Description:窗口中从前面删除一个数
    * @Param: [] 
    * @return: int 
    * @Date: 2020/4/6-12:15
    */
    public int delete(){
        int res = 0;
        //删除的正是窗口中的最大值
        if(left == list.peekFirst()){
            res = arr[list.pollFirst()];
        }
        left--;
        return res;
    }
    /** 
    * @Author: ZwZ
    * @Description:双端链表中的队列头存储的始终是当前窗口的最大值 
    * @Param: [] 
    * @return: int 
    * @Date: 2020/4/6-12:21
    */
    public int getMax(){
        if(!list.isEmpty())
            return list.peekFirst();
        else
            return -1;
    }
}
