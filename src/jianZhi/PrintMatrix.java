package jianZhi;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/1/21 - 12:49
 * @Description:顺时针打印矩阵 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrix {
    /*
    定义四个变量代表范围，up、down、left、right
    向右走存入整行的值，当存入后，该行再也不会被遍历，代表上边界的 up 加一，同时判断是否和代表下边界的 down 交错
    向下走存入整列的值，当存入后，该列再也不会被遍历，代表右边界的 right 减一，同时判断是否和代表左边界的 left 交错
    向左走存入整行的值，当存入后，该行再也不会被遍历，代表下边界的 down 减一，同时判断是否和代表上边界的 up 交错
    向上走存入整列的值，当存入后，该列再也不会被遍历，代表左边界的 left 加一，同时判断是否和代表右边界的 right 交错*/
    public ArrayList<Integer> printMatrix1(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return list;
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[up][i]);
            }
            up++;
            if (up > down)
                break;
            for (int i = up; i <= down; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            if (right < left)
                break;
            for (int i = right; i >= left; i--) {
                list.add(matrix[down][i]);
            }
            down--;
            if (down < up)
                break;
            for (int i = down; i >= up; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            if (left > right)
                break;
        }
        return list;
    }
    /**
     * @Author: ZwZ
     * @Description:若给出左上角的点和右下角的点，打印它们所围成的矩形上的所有值 打印完毕之后，再将两个点向内移(col和row减1) 
     * @Param: [arr] 
     * @return: java.util.ArrayList<java.lang.Integer> 
     * @Date: 2020/3/25-10:41
     */
    public ArrayList<Integer> printMatrix2(int[][] arr) {
        int row = arr.length;//行数
        int col = arr[0].length;//列数
        int leftUpRow = 0;//左上角行
        int leftUpCol = 0;//左上角列
        int rightDownRow = row - 1;//右下角行数
        int rightDownCol = col - 1;//右下角列数
        ArrayList<Integer> list = new ArrayList<>();
        while (leftUpRow <= rightDownRow && leftUpCol <= rightDownCol) {
            printCurve(list, arr, leftUpRow++, leftUpCol++, rightDownRow--, rightDownCol--);
        }
        return list;
    }
    //打印一圈
    private void printCurve(ArrayList<Integer> list, int[][] arr, int leftUpRow, int leftUpCol, int rightDownRow, int rightDownCol) {
        int leftCol = leftUpCol;//临时变量 防止形参中的leftUpRow和leftUpCol值改变(下面要用到)
        int leftRow = leftUpRow;
        //处理边界 即特殊情况 (只给了一行)
        if (leftRow == rightDownRow) {
            while (leftUpCol <= rightDownCol) {
                list.add(arr[leftRow][leftUpCol++]);
            }
        }
        //处理边界 即特殊情况(只给了一列)
        else if (leftCol == rightDownCol) {
            while (leftRow <= rightDownRow) {
                list.add(arr[leftRow++][leftCol]);
            }

        } else {
            //环状打印一圈
            while (leftCol < rightDownCol) {
                list.add(arr[leftRow][leftCol++]);
            }
            while (leftRow < rightDownRow) {
                list.add(arr[leftRow++][leftCol]);
            }
            while (rightDownCol > leftUpCol) {
                list.add(arr[rightDownRow][rightDownCol--]);
            }
            while (rightDownRow > leftUpRow) {
                list.add(arr[rightDownRow--][rightDownCol]);
            }
        }
    }
    public static void main(String[] args) {
        int[][] array = {{1},{2},{3},{4}};
        PrintMatrix printMatrix = new PrintMatrix();
        ArrayList list = printMatrix.printMatrix2(array);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
