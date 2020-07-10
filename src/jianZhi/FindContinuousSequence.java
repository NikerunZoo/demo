package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/2/8 - 22:01
 * @Description:和为S的连续正数序列 输出所有和为S的连续正数序列
 * 序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class FindContinuousSequence {
    /**
     * @Author: ZwZ
     * @Description:利用等差数列求和的思想 
     * @Param: [sum] 
     * @return: java.util.ArrayList<java.util.ArrayList   <   java.lang.Integer>> 
     * @Date: 2020/2/8-22:48
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum <= 0)
            return null;
        int left = 1;
        int right = 2;
        int currentSum = 0;//当前所有数的总和
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        while (left < right) {
            currentSum = ((right + left) * (right - left + 1)) >> 1;//等差数列和
            //值相等
            if (currentSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i < right + 1; i++)
                    list.add(i);
                resultList.add(list);
                left++;
            } else if (currentSum < sum)
                right++;
            else
                left++;
        }
        return resultList;
    }

    public static void main(String[] args) {
        FindContinuousSequence find = new FindContinuousSequence();
        ArrayList<ArrayList<Integer>> list = find.FindContinuousSequence(6);
        for (ArrayList<Integer> inList: list
             ) {
            for (int i = 0; i < inList.size(); i++) {
                System.out.print(inList.get(i));
                System.out.println("-----------");
            }
        }
    }
}
