package complete.bfs.hard;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 接雨水
 *
    给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。

    说明:

    m 和 n 都是小于110的整数。每一个单位的高度都大于0 且小于 20000。

    示例：

    给出如下 3x6 的高度图:
    [
    [1,4,3,1,3,2],
    [3,2,1,3,2,4],
    [2,3,3,2,3,1]
    ]

    返回 4。


    如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。

    下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
 */
public class trapping_rain_water_ii_407 {

    public int trapRainWater(int[][] heightMap) {
        if(heightMap==null || heightMap.length==0 ||heightMap[0].length==0) return 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>((a, b)->a.height-b.height);
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i=0; i<m; i++) {
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n-1, heightMap[i][n-1]));
        }
        for (int i=0; i<n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heightMap[0][i]));
            queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int row = cell.row+dir[0], col=cell.col+dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height-heightMap[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
                }
            }
        }
        return res;
    }
    private class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}
