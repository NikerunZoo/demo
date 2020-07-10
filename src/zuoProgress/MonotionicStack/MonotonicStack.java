package zuoProgress.MonotionicStack;

import java.util.Stack;

/**
 * @author - zwz
 * @date - 20-4-9-下午9:44
 * @Description:单调栈结构的使用
 * 题目：得到一个数组中每个位置上的数的左边和右边离它最近的比它大的值
 */
public class MonotonicStack {
    private class Res{
        private Integer left;
        private Integer right;
        public Res(Integer left,Integer right){
            this.left = left;
            this.right = right;
        }
    }
    /*
     * @Author: ZwZ
     * @Description:使用单调栈结构完成
     * 栈中从栈底到栈顶依次存储从大到小的数据(的下标)
     * 当一个数要进来时，如果没有打破从大到小的顺序，就入栈
     * 如果会打破这个顺序，那么就将栈顶元素出栈，此时出栈的元素的左边比它大的离它最近的值就是
     * 它出栈之后栈顶的元素，右边的比它大的离它最近的值就是打破这个顺序的那个元素
     * @Date: 下午9:48 20-4-9
     * @Param: [arr]
     * @return: zuoProgress.MonotionicStack.MonotonicStack.Res[]
     **/
    public Res[] getMax(int[] arr){
        if(arr == null || arr.length == 0)
            return null;
        Stack<Integer> stack = new Stack<>();
        Res[] result = new Res[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(stack.isEmpty() || arr[stack.peek()] > arr[i])
                stack.push(i);
            else if(arr[stack.peek()] < arr[i]){
                while(arr[stack.peek()] < arr[i]){
                    int temp = stack.pop();
                    if(!stack.isEmpty())
                        result[temp] = new Res(arr[stack.peek()],arr[i]);
                    else
                        result[temp] = new Res(null,arr[i]);
                }
                stack.push(i);
            }else{//相等时应该在stack的一个位置存储这多个数的arr下标，此处先不考虑

            }
        }
        while(!stack.isEmpty()){
            int temp = stack.pop();
            if(!stack.isEmpty())
                result[temp] = new Res(arr[stack.peek()],null);
            else
                result[temp] = new Res(null,null);
        }
        return result;
    }
}
