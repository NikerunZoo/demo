package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/10 - 18:18
 * @Description:替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    //使用API对其进行替换
    public String replaceSpace1(StringBuffer str) {
        String string = str.toString().replace(" ","%20");
        return string;
    }
    //遇到" "追加"%20" 否则遇到什么追加什么
    public String replaceSpace2(StringBuffer str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == ' '){
                sb.append("%20");
            }
            else
                sb.append(c);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("We Are Happy");
        ReplaceSpace rs = new ReplaceSpace();
        String s = rs.replaceSpace1(sb);
        System.out.println(s);
    }
}
