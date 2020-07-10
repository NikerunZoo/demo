package jianZhi;

/**
 * @author - ZwZ
 * @date - 2020/1/10 - 12:50
 * @Description:二维数组中的查找
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Find {
    //暴力解法
    public boolean Find1(int target, int[][] array) {
        int temp = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == target)
                        temp++;
                }
            }
            if (temp != 0)
                return true;
            else
                return false;
    }
    /**
     * 从左下角找
     * 当 m < target，由于 m 已经是该列最大的元素，想要更大只有从列考虑，取值右移一位
     * 当 m > target，由于 m 已经是该行最小的元素，想要更小只有从行考虑，取值上移一位
     * 当 m = target，找到该值，返回 true
     * */
    public boolean Find2(int target, int[][] array) {
        int rows = array.length;//行数
        int cols = array[0].length;//列数
        int row = rows - 1;
        int col = 0;
        if(rows == 0 || cols == 0)
            return false;
        while(row >= 0 && col < cols ){
            if(array[row][col] < target)
                col++;
            else if(array[row][col] > target)
                row--;
            else
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Find solution = new Find();
        System.out.println(solution.Find1(13, array));
        System.out.println(solution.Find2(12,array));
    }
}
