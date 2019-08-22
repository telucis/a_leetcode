package complete.twoPointer.reversePointer;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 最接近的三数之和
 *
    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

    例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

    与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

 */
public class three_sum_closest_16 {

    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int res = -1;
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++) {
            int newT = target - nums[i];
            int left = i+1, right = nums.length-1;
            while (left<right) {
                int tmp = nums[left]+nums[right];
                if (tmp==newT) {
                    return target;
                } else if (tmp>newT) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(newT - tmp)<min) {
                    min = Math.abs(newT - tmp);
                    res = tmp+nums[i];
                }
            }
        }
        return res;
    }
}
