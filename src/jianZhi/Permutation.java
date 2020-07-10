package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/1/27 - 16:55
 * @Description:字符串的排列
 * 输入一个字符串, 按字典序打印出该字符串中字符的所有排列
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class Permutation {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null)
            return result;
        if (str.length() == 1) {
            result.add(str);
            return result;
        }
        char temp;
        //String不可变，只能先变为StringBuilder(效率高)/StringBuffer(线程安全)
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (i == 0 || stringBuilder.charAt(0) != stringBuilder.charAt(i)) {
                //交换
                temp = stringBuilder.charAt(i);
                stringBuilder.setCharAt(i,stringBuilder.charAt(0));
                stringBuilder.setCharAt(0,temp);
                ArrayList<String> newResult = Permutation(stringBuilder.substring(1));
                //将此次结果添加到最终待返回的结果中
                for (int j = 0; j < newResult.size(); j++) {
                    result.add(stringBuilder.charAt(0)+newResult.get(j));
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        String str = "abc";
        ArrayList list = permutation.Permutation(str);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
