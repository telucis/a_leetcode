package complete.bfs.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 岛屿的最大面积
 *
    给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

    找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

    示例 1:

    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
    [0,0,0,0,0,0,0,1,1,1,0,0,0],
    [0,1,1,0,1,0,0,0,0,0,0,0,0],
    [0,1,0,0,1,1,0,0,1,0,1,0,0],
    [0,1,0,0,1,1,0,0,1,1,1,0,0],
    [0,0,0,0,0,0,0,0,0,0,1,0,0],
    [0,0,0,0,0,0,0,1,1,1,0,0,0],
    [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

    示例 2:

    [[0,0,0,0,0,0,0,0]]
    对于上面这个给定的矩阵, 返回 0。

    注意: 给定的矩阵grid 的长度和宽度都不超过 50。


 */
public class max_area_of_island_695 {

    public int maxAreaOfIsland(int[][] grid) {
        int m=grid.length, n=grid[0].length;
        int ans = 0;
        boolean[][] visited = new boolean[m][n];
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]==1) {
                    list.add(i*n+j);
                }
            }
        }
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        for (Integer item : list) {
            if (visited[item/n][item%n]) {
                continue;
            }
            int area = 1;
            visited[item/n][item%n] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(item);
            while (!queue.isEmpty()) {
                Integer node = queue.poll();
                for (int i=0; i<4; i++) {
                    int x = node/n+dx[i];
                    int y = node%n+dy[i];
                    if (judge(grid, visited, x, y, m, n)) {
                        area++;
                        visited[x][y] = true;
                        queue.offer(x*n+y);
                    }
                }
            }
            ans = Math.max(ans, area);
        }
        return ans;
    }
    private boolean judge(int[][] grid, boolean[][] visited, int x, int y, int m, int n) {
        if (x>=0 && x<m && y>=0 && y<n && grid[x][y]==1 && !visited[x][y]) {
            return true;
        }
        return false;
    }
}
