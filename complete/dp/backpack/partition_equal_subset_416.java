package complete.dp.backpack;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 分割等和子集
 *
    给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

    注意:

    每个数组中的元素不会超过 100
    数组的大小不会超过 200
    示例 1:

    输入: [1, 5, 11, 5]

    输出: true

    解释: 数组可以分割成 [1, 5, 5] 和 [11].


    示例 2:

    输入: [1, 2, 3, 5]

    输出: false

    解释: 数组不能分割成两个元素和相等的子集.



 */
public class partition_equal_subset_416 {

    /**
     * 01 backpack
     * dp[nums.len+1][sum/2+1]
     * dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[j-1]]
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length, sum=0;
        for (int i : nums) {
            sum+=i;
        }
        if ((sum&1)==1) return false;
        sum /= 2;
        boolean[][] dp = new boolean[n+1][sum+1];
        for (int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        for (int j=1; j<=sum; j++) {
            dp[0][j] = false;
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j>=nums[i-1]) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }
        return dp[n][sum];
    }
}
