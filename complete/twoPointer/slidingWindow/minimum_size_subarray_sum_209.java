package complete.twoPointer.slidingWindow;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 长度最小的子数组
 *
    给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

    示例:

    输入: s = 7, nums = [2,3,1,2,4,3]
    输出: 2
    解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
    进阶:

    如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。


 */
public class minimum_size_subarray_sum_209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length==0) {
            return 0;
        }
        int left = 0, right = 0, tmp = 0;
        int ans = Integer.MAX_VALUE;
        while (right<nums.length) {
            while (right<nums.length && tmp<s) {
                tmp += nums[right];
                right++;
            }
            while (tmp>=s) {
                ans = Math.min(right-left, ans);
                tmp -= nums[left];
                left++;
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }
}
