package complete.dfs.hard;

/**
 * @author karl.wy
 * @date 2019/05/26
 *
 * 最大人工岛
 *
    在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。

    进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）

    示例 1:

    输入: [[1, 0], [0, 1]]
    输出: 3
    解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
    示例 2:

    输入: [[1, 1], [1, 0]]
    输出: 4
    解释: 将一格0变成1，岛屿的面积扩大为 4。
    示例 3:

    输入: [[1, 1], [1, 1]]
    输出: 4
    解释: 没有0可以让我们变成1，面积依然为 4。
    说明:

    1 <= grid.length = grid[0].length <= 50
    0 <= grid[i][j] <= 1

 */
public class making_a_large_island_827 {

    public int largestIsland(int[][] grid) {
        int max=0, m=grid.length, n=grid[0].length;
        boolean hasZero = false;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j]==0) {
                    grid[i][j]=1;
                    max=Math.max(max, dfs(i, j, grid, new boolean[m][n]));
                    if (max==m*n) return max;
                    grid[i][j]=0;
                    hasZero=true;
                }
            }
        }
        return hasZero ? max : m*n;
    }
    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0||visited[i][j]) return 0;
        visited[i][j]=true;
        int result = 1+dfs(i-1,j,grid,visited)+dfs(i+1,j,grid,visited)+dfs(i,j+1,grid,visited)+dfs(i,j-1,grid,visited);
        return result;
    }
}
