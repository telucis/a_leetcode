package complete.dfs.mid.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 出界的路径数
 *
    给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。



    示例 1：

    输入: m = 2, n = 2, N = 2, i = 0, j = 0
    输出: 6
    解释:

    示例 2：

    输入: m = 1, n = 3, N = 3, i = 0, j = 1
    输出: 12
    解释:



    说明:

    球一旦出界，就不能再被移动回网格内。
    网格的长度和高度在 [1,50] 的范围内。
    N 在 [0,50] 的范围内。

 */
public class out_of_boundary_paths_576 {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int mod = 1000000000 + 7;
    public int findPaths(int m, int n, int N, int i, int j) {
        // m * n grid
        long[][][] memo = new long[m][n][N+1];
        for (int ii = 0; ii < m; ii++) {
            for (int jj = 0; jj < n; jj++) {
                for (int kk = 0; kk < N+1; kk++) {
                    memo[ii][jj][kk] = -1;
                }
            }
        }
        return (int) (dfs(m, n, N, i, j, memo) % mod);
    }

    public long dfs(int m, int n, int N, int i, int j, long[][][] memo) {
        //check if out of boundary, if out could not move back
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (N == 0) return 0;
        if (memo[i][j][N] != -1) return memo[i][j][N];
        memo[i][j][N] = 0;
        for (int dir[] : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            memo[i][j][N] = (memo[i][j][N] + dfs(m, n, N - 1, x, y, memo) % mod) % mod;
        }
        return memo[i][j][N];
    }


    /**
     * wrong solution
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int findPaths2(int m, int n, int N, int i, int j) {
        int ans = 0;
        if (n==0 || m==0 || N==0) return ans;
        if (i<0 || j<0 || i>=m || j>=n) return ans;
        if (map.containsKey(m*n*N+i*n+j)) return map.get(m*n*N+i*n+j);
        if (i==0) ans++;
        if (i==m-1) ans++;
        if (j==0) ans++;
        if (j==n-1) ans++;
        ans += findPaths(m, n, N-1, i-1, j)%mod;
        ans += findPaths(m, n, N-1, i+1, j)%mod;
        ans += findPaths(m, n, N-1, i, j+1)%mod;
        ans += findPaths(m, n, N-1, i, j-1)%mod;
        map.put(m*n*N+i*n+j, ans%mod);
        return ans%mod;
    }
}
