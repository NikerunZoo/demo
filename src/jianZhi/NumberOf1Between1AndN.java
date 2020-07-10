package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/31 - 19:41
 * @Description:整数中1出现的次数（从1到n整数中1出现的次数）
 * 输入一个整数n, 求1~n这n个整数的十进制表示中1出现的次数
 * 例如：1~12中包含1的数字有1,10,11,12,所以1一共出现了5次
 */
public class NumberOf1Between1AndN {
    /** 
    * @Author: ZwZ
    * @Description:对每个数单独进行计算，然后把每个数中计算出的1的个数进行累加 
    * @Param: [n] 
    * @return: int 
    * @Date: 2020/1/31-20:16
    */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += number(i);
        }
        return count;
    }
    /** 
    * @Author: ZwZ
    * @Description:对每一个具体数中的1的个数进行计算 
    * @Param: [n] 
    * @return: int 
    * @Date: 2020/1/31-20:14
    */
    public int number(int n){
        int count = 0;
        while (n != 0) {
            //判断个位数是否为 1
            if (n % 10 == 1)
                count++;
            n = n / 10;
        }
        return count;
    }
    public static void main(String[] args) {
        NumberOf1Between1AndN number = new NumberOf1Between1AndN();
        System.out.println(number.NumberOf1Between1AndN_Solution(12));
    }
}
