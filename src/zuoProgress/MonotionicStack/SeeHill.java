package zuoProgress.MonotionicStack;

import java.util.Stack;

/**
 * @author - zwz
 * @date - 20-4-10-下午4:33
 * @Description:烽火台问题（京东） 【太难】
 * 题目描述：
 * 战争游戏的至关重要环节就要到来了，这次的结果将决定王国的生死存亡，小B负责首都的防卫工作。首都处于一个四面环山的盆地中，
 * 周围的n个小山构成一个环，作为预警措施，小B计划在每个小山上设置一个观察哨，日夜不停的瞭望周围发生的情况。
 * 一旦发生外敌入侵事件，山顶上的岗哨将点燃烽烟。若两个岗哨所在的山峰之间没有更高的山峰遮挡且两者之间有相连通路
 * 则岗哨可以观察到另一个山峰上的烽烟是否点燃。由于小山处于环上，任意两个小山之间存在两个不同的连接通路
 * 满足上述不遮挡的条件下，一座山峰上岗哨点燃的烽烟至少可以通过一条通路被另一端观察到
 * 对于任意相邻的岗哨，一端的岗哨一定可以发现一端点燃的烽烟。
 * 小B设计的这种保卫方案的一个重要特性是能够观测到对方烽烟的岗哨对的数量，她希望你能够帮她解决这个问题
 * 输入：
 * 输入中有多组测试数据。每组测试数据的第一行为一个整数n（3<=n <=10^6），为首都周围的小山数量
 * 第二行为n个整数，依次表示小山的高度h（1<=h<=10^9）。
 * 输出：
 * 对每组测试数据，在单独的一行中输出能相互观察到的岗哨的对数
 * 样例输入
 * 5
 * 1 2 4 5 3
 * 样例输出
 * 7
 * 题目压缩：
 * 给一个数组，代表环形的山
 * 1、每座山上会放烽火，相邻可看见烽火
 * 2、不相邻的山峰，两条路中其中一条路上的烽火都不大于他们之间的最小值，就能看见
 * 返回能相互看见的山峰有多少对？
 */
public class SeeHill {
    /*
     * @Author: ZwZ
     * @Description:如果数组中没有重复元素
     * 找规律：
     * 找到环形中的最大值和次大值，那么其他位置的元素，顺时针和逆时针肯定都会有比此元素大的值出现
     * 第一个出现的比他高的山峰，就是这个元素可以看到的山，每个位置都可以向左向右看到两个位置
     * (有可能是最大值和次大值，也有可能是此元素通向他们的道路上遇到的某个值) 所以共(arr.length - 2)*2个
     * 再加上最高和此高这一对
     * @Date: 下午4:49 20-4-10
     * @Param: [arr]
     * @return: int
     **/
    public int getCommunications1(int[] arr){
        if(arr == null || arr.length <= 1)
            return 0;
        else
            return (arr.length - 2)*2 + 1;
    }
    /*
     * @Author: ZwZ
     * @Description:当数组中会存在重复元素时，使用单调栈结构
     * 由于这里使用单调栈目的是找到比某个数大的第一个左右两边的值，所以元素从栈底到栈顶从大到小排序
     * 单调栈中存储每个值，和每个值出现的次数
     * @Date: 下午4:54 20-4-10
     * @Param: [arr]
     * @return: int
     **/
    public long getCommunications2(int[] arr){
        if(arr == null || arr.length <= 1)
            return 0;
        int size = arr.length;
        int maxIndex = 0;
        //寻找数组中最大值的下标
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        int index = nextIndex(size,maxIndex);//maxIndex下一个位置
        long res = 0L;
        Stack<Pair> stack = new Stack();
        stack.push(new Pair(value));//最大值入栈
        //遍历一圈
        while(index != maxIndex){
            value = arr[index];//当前值
            while(!stack.isEmpty() && stack.peek().value < value){
                int times = stack.pop().times;
                res += getInternalSum(times) + 2*times;//C(2,times) + 每个山峰和两边的比他们高的山峰又可以形成的两对
            }
            //当前值和栈顶值相等
            if(!stack.isEmpty() && stack.peek().value == value)
                stack.peek().times++;
            //当前值小于栈顶值
            else
                stack.push(new Pair(value));
            index = nextIndex(size,index);
        }
        while(!stack.isEmpty()){
            int times = stack.pop().times;
            res += getInternalSum(times);//先加上山峰内部之间的对数
            /**如果不是栈底倒数第一二行，依旧和之前一样：C(2,times) + 2*times
             * 是倒数第二行：
             *  如果栈底(倒数第一行)的数出现的次数为1，则C(2,times) + times
             *  如果栈底的数出现的次数>=2,则C(2,times) + 2*times
             * 如果是倒数第一行(全局最大值)：C(2,times)
             * */
            if(!stack.isEmpty()){
                res += times;
                if(stack.size() > 1)
                    res += times;
                else
                    res += stack.peek().times > 1 ? times : 0;
            }
        }
        return res;
    }
    private class Pair{
        private int value;
        private int times;//value出现次数
        public Pair(int value){
            this.value = value;
            this.times = 1;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:得到当前位置的下一个位置
     * @Date: 下午5:07 20-4-10
     * @Param: [size, i]
     * @return: int
     **/
    private static int nextIndex(int size,int i){
        return i < (size - 1) ? (i + 1) : 0;
    }
    /*
     * @Author: ZwZ
     * @Description:得到存在相同值的times个山峰内部满足题意的山峰对数
     * times个山峰中每两个山峰之间都可以相互看见（即数学上的C2n）.
     * @Date: 下午5:15 20-4-10
     * @Param: [times]
     * @return: int
     **/
    private static long getInternalSum(int times){
        return times == 1L ? 0L : (long) times*(long)(times - 1) /2L;
    }
}
