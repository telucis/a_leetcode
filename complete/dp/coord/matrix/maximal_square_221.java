package complete.dp.coord.matrix;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 最大正方形
 *
    在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

    示例:

    输入:

    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0

    输出: 4

 */
public class maximal_square_221 {

    public int maximalSquare(char[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 || j==0) dp[i][j] = matrix[i][j]-'0';
                else {
                    if (matrix[i][j]=='0') dp[i][j]=0;
                    else {
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans * ans;
    }
}
