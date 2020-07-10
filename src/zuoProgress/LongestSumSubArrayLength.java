package zuoProgress;

import java.util.HashMap;
/*
 * @Author: ZwZ
 * @Description:一个数组中存在正数，负数和0，计算子数组累加和为K的最长子数组的长度
 * 例如：7 3 2 1 1 6
 * K = 7,则返回4
 * @Date: 下午3:16 20-4-12
 **/
public class LongestSumSubArrayLength {
	/*
	 * @Author: ZwZ
	 * @Description:求出以每个位置结尾的情况下，累加和为aim的最长子数组
	 * 那么最终要返回的结果一定在这所有求得的最长子数组中
	 * 假设aim==800,要求以1000位置结尾，累加和为aim的最长子数组
	 * 且此时sum == 2000（0--1000位置的累加和）那么求出在哪个位置最先出现sum==1200
	 * 那么以1000结尾的累加和为aim的最长子数组就是此位置的下一个位置到1000位置
	 * @Date: 下午3:18 20-4-12
	 * @Param: [arr, aim]
	 * @return: int
	 **/
	public static int maxLength(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap();//key:某累加和 value:此累加和最早出现的位置
		map.put(0, -1); // 一个数都没有的时候，也可以累加出0所以要记录此位置
		int res = 0;
		int sum = 0;//从0位置累加到i位置的总和
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - aim)) {
				res = Math.max(i - map.get(sum - aim), res);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return res;
	}
	/*
	 * @Author: ZwZ
	 * @Description:本题的变型
	 * 给定一个数组，数组内只有奇数和偶数，求基数个数==偶数个数的最长子数组的长度
	 * 思路：将数组中所有存储奇数的位置上的值改成1，偶数位置上的值改成-1,
	 * 此题变为：求数组中子数组和为0的最长子数组的长度
	 * @Date: 下午3:51 20-4-12
	 * @Param: [arr]
	 * @return: int
	 **/
	public int change(int[] arr){


		return 0;
	}
	/*
	 * @Author: ZwZ
	 * @Description:生成随机数组
	 * @Date: 下午3:48 20-4-12
	 * @Param: [size]
	 * @return: int[]
	 **/
	public static int[] generateArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 11) - 5;
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(maxLength(generateArray(20), 10));
	}

}
