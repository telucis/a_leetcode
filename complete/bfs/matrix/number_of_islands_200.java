package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 岛屿的个数
 *
    给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

    示例 1:

    输入:
    11110
    11010
    11000
    00000

    输出: 1
    示例 2:

    输入:
    11000
    11000
    00100
    00011

    输出: 3

 */
public class number_of_islands_200 {

    public int numIslands(char[][] grid) {
        int ans = 0;
        if (grid==null || grid.length==0 || grid[0].length==0) {
            return ans;
        }
        int m = grid.length, n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1') {
                   ans++;
                   bfs(grid, i, j);
                }
            }
        }
        return ans;
    }
    private void bfs(char[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        grid[x][y] = '0';
        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            for (int i=0; i<4; i++) {
                int xx = item[0]+dx[i];
                int yy = item[1]+dy[i];
                if (xx>=0 && xx<m && yy>=0 &&yy<n && grid[xx][yy]=='1') {
                    grid[xx][yy]='0';
                    queue.offer(new int[]{xx, yy});
                }
            }
        }
    }
}
