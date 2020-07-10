package zuoProgress.MonotionicStack;

import java.util.Stack;

/**
 * @author - zwz
 * @date - 20-4-10-下午2:23
 * @Description:求最大矩阵的大小
 * 规定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，最大的矩形区域中1的数量
 * 例如：1110
 * 其中最大的矩形区域有3个1，所以返回3
 * 1011
 * 1111
 * 1110其中最大的矩形区域有6个1，所以返回6
 * 思路：使用单调栈结构 时间复杂度O(M*N)
 */
public class GetMaxMatrix {
    /*
     * @Author: ZwZ
     * @Description:利用maxRecFromBottom的思路，求得以每一行为底的所有子矩阵中的最大面积即是所要结果
     * @Date: 下午3:37 20-4-10
     * @Param: [map]
     * @return: int
     **/
    public static int maxRecSize(int[][] map){
        if(map == null || map.length == 0 || map[0].length == 0)
            return 0;
        int maxArea = 0;
        int[] arr = new int[map[0].length];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                arr[col] = map[row][col] == 0 ? 0 : arr[col] + 1;
            }
            maxArea = Math.max(maxArea,maxRecFromBottom(arr));
        }
        return maxArea;
    }


    /*
     * @Author: ZwZ
     * @Description:例子：[4 3 2 5 6]
     * 这个可以看成：有5排由正方形组成的长方形，他们的高度分别是4 3 2 5 6
     * 求这个物体中所能画出的最大的长方形的面积(即最大的长方形由几个小正方形组成，本体就是此问题的变体)
     * 而此方法在本题目中作用：求以某一行及其上面所有的行中，所构成的矩形的最大的面积
     * @Date: 下午3:17 20-4-10
     * @Param: [arr]
     * @return: int
     **/
    private static int maxRecFromBottom(int[] arr){
        if(arr == null || arr.length == 0)
            return 0;
        int res = 0;
        Stack<Integer> monotionicStack = new Stack<>();//单调栈，数据从栈底到栈顶遵循从小到大
        for (int i = 0; i < arr.length; i++) {
            //结算某一个位置
            while(!monotionicStack.isEmpty() && arr[monotionicStack.peek()] >= arr[i]){
                int curIndex = monotionicStack.pop();//当前所出栈元素在数组中的下标
                int left = monotionicStack.isEmpty() ? -1 : monotionicStack.peek();//当前出来的数的左边界（右边界就是i）
                int curArea = (i - left - 1) * arr[curIndex];//当前出栈元素可以向左向右扩的格子长度*高度
                res = Math.max(curArea,res);
            }
            monotionicStack.push(i);
        }
        //处理栈中剩余元素
        while(!monotionicStack.isEmpty()){
            int curIndex = monotionicStack.pop();
            int left = monotionicStack.isEmpty() ? -1 : monotionicStack.peek();//左边界
            int curArea = (arr.length - left - 1) * arr[curIndex];
            res = Math.max(res,curArea);
        }
        return res;
    }
}
