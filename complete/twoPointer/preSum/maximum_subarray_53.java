package complete.twoPointer.preSum;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 最大子序和
 *
    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例:

    输入: [-2,1,-3,4,-1,2,1,-5,4],
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    进阶:

    如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。


 */
public class maximum_subarray_53 {

    /**
     * dp
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i=1; i<nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i]+dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
