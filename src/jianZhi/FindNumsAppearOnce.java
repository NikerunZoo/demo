package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/2/6 - 13:36
 * @Description:数组中只出现一次的数字 (较难)
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次
 * 请写程序找出这两个只出现一次的数字
 */
public class FindNumsAppearOnce {
    /** 
    * @Author: ZwZ
    * @Description:通过异或运算 (解析见剑指offer)
    * @Param: [array, num1, num2] num1,num2分别为长度为1的数组。传出参数 将num1[0],num2[0]设置为返回结果
    * @return: void 
    * @Date: 2020/2/6-13:37
    */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array == null || array.length == 0)
            return;
        int result = array[0];
        //将数组中所有元素异或运算
        for(int i = 1;i < array.length;i++)
            result = result^array[i];
        int index = 1;
        while((index & result) == 0)
            index = index<<1;
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < array.length; i++) {
            if((index & array[i]) == 0)
                result1 = result1 ^ array[i];
            else
                result2 = result2 ^ array[i];
        }
        num1[0] = result1;
        num2[0] = result2;
    }
}
