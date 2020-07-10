package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/18 - 21:43
 * @Description:数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 */
public class Power {
    public double Power1(double base, int exponent) {
        double count = 1;//最终结果
        if(exponent >= 0){
            for (int i = 0; i < exponent; i++) {
                count *=base;
            }
            return count;
        }
        //当base==0若不单独处理，下面会出现无穷大的情况
        else if(base == 0)
            return 0;
        else{
            exponent = -exponent;
            for (int i = 0; i < exponent; i++) {
                count *=base;
            }
            return 1.0/count;
        }
    }
    //对上方法进行优化(详见剑指P113) 时间复杂度logN
    public double Power2(double base, int exponent) {
        double result = 1;//最终结果
        boolean flag = false;
        if(exponent < 0){
            flag = true;
            exponent = -exponent;
        }
        while(exponent > 0){
            /*位与运算代替了求余运算符 来判断exponent奇偶
            当exponent为基数时，要单独乘一个base*/
            if((exponent & 1) == 1){
                result = result*base;
            }
            exponent >>=1;//右移运算符代替除以2
            base *= base;
        }
        if(flag){
            return 1.0/result;
        }else{
            return result;
        }
    }
}
