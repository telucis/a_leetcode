package complete.dp.coord.matrix;

import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 三角形最小路径和
 *
 *
    给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

    例如，给定三角形：

    [
    [2],
    [3,4],
    [6,5,7],
    [4,1,8,3]
    ]
    自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

    说明：

    如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。


 */
public class triangle_120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[][] dp = new int[m][m];
        dp[0][0] = triangle.get(0).get(0);
        for (int i=1; i<m; i++) {
            dp[i][0] = triangle.get(i).get(0) + dp[i-1][0];
            dp[i][i] = triangle.get(i).get(i) + dp[i-1][i-1];
        }
        for (int i=1; i<m; i++) {
            for (int j=1; j<i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i-1][j-1], dp[i-1][j]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            min = Math.min(min, dp[m-1][i]);
        }
        return min;
    }
}
