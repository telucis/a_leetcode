package complete.dfs.mid.traversal;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 边框着色
 *
    给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。

    只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。

    连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。

    给出位于 (r0, c0) 的网格块和颜色 color，使用指定颜色 color 为所给网格块的连通分量的边界进行着色，并返回最终的网格 grid 。



    示例 1：

    输入：grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
    输出：[[3, 3], [3, 2]]
    示例 2：

    输入：grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
    输出：[[1, 3, 3], [2, 3, 3]]
    示例 3：

    输入：grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
    输出：[[2, 2, 2], [2, 1, 2], [2, 2, 2]]


    提示：

    1 <= grid.length <= 50
    1 <= grid[0].length <= 50
    1 <= grid[i][j] <= 1000
    0 <= r0 < grid.length
    0 <= c0 < grid[0].length
    1 <= color <= 1000



 */
public class coloring_a_border_1034 {

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        dfs(grid, r0, c0, grid[r0][c0]);
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                grid[i][j] = grid[i][j]<0 ? color : grid[i][j];
            }
        }
        return grid;
    }
    private void dfs(int[][] grid, int r, int c, int c1) {
        if (r<0 || c<0 || r>=grid.length || c>=grid[0].length || grid[r][c]!=c1) return;
        grid[r][c] = -c1;
        dfs(grid, r-1, c, c1);
        dfs(grid, r+1, c, c1);
        dfs(grid, r, c-1, c1);
        dfs(grid, r, c+1, c1);
        if (r>0 && r<grid.length-1 && c>0 && c<grid[0].length-1 &&
            c1==Math.abs(grid[r-1][c]) &&
            c1==Math.abs(grid[r+1][c]) &&
            c1==Math.abs(grid[r][c+1]) &&
            c1==Math.abs(grid[r][c-1])) {
            grid[r][c]=c1;
        }
    }
}
