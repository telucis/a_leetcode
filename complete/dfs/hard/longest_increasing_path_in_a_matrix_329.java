package complete.dfs.hard;

/**
 * @author karl.wy
 * @date 2019/05/25
 *
 * 矩阵中的最长递增路径
 *
    给定一个整数矩阵，找出最长递增路径的长度。

    对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

    示例 1:

    输入: nums =
    [
    [9,9,4],
    [6,6,8],
    [2,1,1]
    ]
    输出: 4
    解释: 最长递增路径为 [1, 2, 6, 9]。
    示例 2:

    输入: nums =
    [
    [3,4,5],
    [3,2,6],
    [2,2,1]
    ]
    输出: 4
    解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

 */
public class longest_increasing_path_in_a_matrix_329 {

    private int[][] direct=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
        int m=matrix.length, n=matrix[0].length;
        int[][] map = new int[m][n];
        int ans=0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                ans = Math.max(ans, dfs(map, matrix, i, j));
            }
        }
        return ans;
    }
    private int dfs(int[][] map, int[][] matrix, int x, int y) {
        if (map[x][y]!=0) return map[x][y];
        int m=matrix.length, n=matrix[0].length;
        int ans = 0;
        for (int i=0; i<direct.length; i++) {
            int xx=x+direct[i][0], yy=y+direct[i][1];
            if (xx<0 || yy<0 || xx>=m || yy>=n || matrix[xx][yy]<=matrix[x][y]) continue;
            ans = Math.max(ans, dfs(map, matrix, xx, yy));
        }
        ans+=1;
        map[x][y]=ans;
        return ans;
    }
}
