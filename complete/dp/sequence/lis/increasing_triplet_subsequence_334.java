package complete.dp.sequence.lis;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/29
 *
 * 递增的三元子序列
 *
    给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

    数学表达式如下:

    如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
    使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
    说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。

    示例 1:

    输入: [1,2,3,4,5]
    输出: true
    示例 2:

    输入: [5,4,3,2,1]
    输出: false

 */
public class increasing_triplet_subsequence_334 {

    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n<=a) a=n;
            else if (n<=b) b=n;
            else return true;
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n<3) return false;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i]>nums[j] && dp[j]+1>dp[i]) {
                    dp[i] = dp[j]+1;
                    max = Math.max(max, dp[j]+1);
                    if (max==3) return true;
                }
            }
        }
        return false;
    }
}
