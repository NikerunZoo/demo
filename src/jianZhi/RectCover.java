package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/15 - 10:59
 * @Description:矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少 f种方法
 */
public class RectCover {
    /*第 n 级横着覆盖，用了一级，剩下 n = n - 1，所以关注第 n - 1 种有几种覆盖方法
    第 n 级竖着覆盖，用了两级，剩下 n = n - 2，所以关注第 n - 2 种*/
    public int RectCover(int target) {
        if(target<=2)
            return target;
        else
            return RectCover(target-1) + RectCover(target-2);
    }
}
