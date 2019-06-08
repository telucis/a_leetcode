package complete.math.combination;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 完全平方数
 *
    给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

    示例 1:

    输入: n = 12
    输出: 3
    解释: 12 = 4 + 4 + 4.
    示例 2:

    输入: n = 13
    输出: 2
    解释: 13 = 4 + 9.

 */
public class prefect_squares_279 {

    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i=1; i<=n; i++) {
            int min = Integer.MAX_VALUE;
            int j=1;
            while (i-j*j>=0) {
                min = Math.min(min, dp[i-j*j]+1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
