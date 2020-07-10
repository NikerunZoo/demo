package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/2/3 - 13:55
 * @Description:第一个只出现一次的字符
 * 在一个字符串(0 < = 字符串长度 < = 10000,全部由字母组成)中找到第一个只出现一次的字符
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstNotRepeatingChar {
    /** 
    * @Author: ZwZ
    * @Description：巧用ASCII值 
    * @Param: [str] 
    * @return: int 
    * @Date: 2020/2/3-21:59
    */
    public int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0)
            return -1;
        //A-Z对应的ASCII码为65-90，a-z对应的ASCII码值为97-122
        int[] arr = new int[58];
        //通过每个字母的ascii码值进行存储
        for(int i = 0;i < str.length();i++){
            arr[str.charAt(i) - 65]++;
        }
        for(int i = 0;i < str.length();i++){
            if(arr[str.charAt(i) - 65]  == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar firstNotRepeatingChar = new FirstNotRepeatingChar();
        System.out.println(firstNotRepeatingChar.FirstNotRepeatingChar("NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp"));
    }
}
    