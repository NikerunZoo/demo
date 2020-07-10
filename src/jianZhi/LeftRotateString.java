package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/3/1 - 15:48
 * @Description:对于一个给定的字符序列S，请你把其循环左移K位后的序列输出 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”
 */
public class LeftRotateString {
    /**
     * @Author: ZwZ
     * @Description:借助API 
     * @Param: [str, n] 
     * @return: java.lang.String 
     * @Date: 2020/3/1-15:48
     */
    public String LeftRotateString1(String str, int n) {
        if (str == null)
            return null;
        if (str.length() <= n)
            return str;
        StringBuilder sb = new StringBuilder(str);
        String temp = str.substring(0, n);
        sb.delete(0, temp.length());
        sb.append(temp);
        return sb.toString();
    }
    /** 
    * @Author: ZwZ
    * @Description:借助API 
    * @Param: [str, n] 
    * @return: java.lang.String 
    * @Date: 2020/3/1-15:54
    */
    public String LeftRotateString2(String str, int n) {
        if (str == null)
            return null;
        if (str.length() <= n)
            return str;
        return str.substring(n+1) + str.substring(0, n + 1);

    }
}
