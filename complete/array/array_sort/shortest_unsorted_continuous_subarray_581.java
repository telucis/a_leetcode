package complete.array.array_sort;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 最短无序连续子数组
 *
    给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

    你找到的子数组应是最短的，请输出它的长度。

    示例 1:

    输入: [2, 6, 4, 8, 10, 9, 15]
    输出: 5
    解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    说明 :

    输入的数组长度范围在 [1, 10,000]。
    输入的数组可能包含重复元素 ，所以升序的意思是<=。

 */
public class shortest_unsorted_continuous_subarray_581 {

    public int findUnsortedSubarray(int[] nums) {
        int[] newNums = nums.clone();
        Arrays.sort(newNums);
        int left = 0, right = nums.length-1;
        while (left<nums.length && nums[left] == newNums[left]) {
            left++;
        }
        if (left==nums.length) {
            return 0;
        }
        while (right>=0 && nums[right] == newNums[right]) {
            right--;
        }
        return right-left+1;
    }
}
