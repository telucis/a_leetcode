package complete.dp.memory;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 最大平均值和的分组
 *
    我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

    注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

    示例:
    输入:
    A = [9,1,2,3,9]
    K = 3
    输出: 20
    解释:
    A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
    我们也可以把 A 分成[9, 1], [2], [3, 9].
    这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
    说明:

    1 <= A.length <= 100.
    1 <= A[i] <= 10000.
    1 <= K <= A.length.
    答案误差在 10^-6 内被视为是正确的。

 */
public class largest_sum_of_averages_813 {


    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[] sum = new double[n];
        sum[0] = A[0];
        for (int i=1; i<A.length; i++) {
            sum[i] = sum[i-1] + A[i];
        }
        return backtrack(A, n, sum, 0, K, new double[n][K+1]);
    }
    private double backtrack(int[] A, int len, double[] sum, int start, int k, double[][]dp) {
        if (k==1) return (sum[len-1]-sum[start]+A[start])/(len-start);
        if (dp[start][k] != 0) return dp[start][k];

        for (int i=start; i<=len-k; i++) {
            dp[start][k] = Math.max(dp[start][k], ((sum[i]-sum[start]+A[start])*1.0)/(i-start+1) + backtrack(A, len, sum, i+1, k-1, dp));
        }
        return dp[start][k];
    }
}
