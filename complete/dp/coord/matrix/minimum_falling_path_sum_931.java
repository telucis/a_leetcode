package complete.dp.coord.matrix;

/**
 * Created by telucis on 2019/5/14.
 *
 * 下降路径最小和
 *
     给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。

     下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。



     示例：

     输入：[[1,2,3],[4,5,6],[7,8,9]]
     输出：12
     解释：
     可能的下降路径有：
     [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
     [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
     [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
     和最小的下降路径是 [1,4,7]，所以答案是 12。



     提示：

     1 <= A.length == A[0].length <= 100
     -100 <= A[i][j] <= 100

 */
public class minimum_falling_path_sum_931 {
    public int minFallingPathSum(int[][] A) {
        if (A.length==0 || A[0].length==0) return 0;
        int m=A.length, n=A[0].length;
        int ans=0;
        if (n==1) {
            for (int i=0; i<m; i++) {
                ans+=A[i][0];
            }
            return ans;
        }
        int[][] dp = new int[m][n];
        for (int j=0; j<n; j++) {
            dp[0][j] = A[0][j];
        }
        for (int i=1; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (j==0) dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j+1])+A[i][j];
                else if (j==n-1) dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j])+A[i][j];
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i-1][j+1]))+A[i][j];
                }
            }
        }
        ans=dp[m-1][0];
        for (int j=1; j<n; j++) {
            ans = Math.min(dp[m-1][j], ans);
        }
        return ans;
    }
}
