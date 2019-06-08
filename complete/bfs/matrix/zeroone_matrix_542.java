package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 01 矩阵
 *
    给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

    两个相邻元素间的距离为 1 。

    示例 1:
    输入:

    0 0 0
    0 1 0
    0 0 0
    输出:

    0 0 0
    0 1 0
    0 0 0
    示例 2:
    输入:

    0 0 0
    0 1 0
    1 1 1
    输出:

    0 0 0
    0 1 0
    1 2 1
    注意:

    给定矩阵的元素个数不超过 10000。
    给定矩阵中至少有一个元素是 0。
    矩阵中的元素只在四个方向上相邻: 上、下、左、右。

 */
public class zeroone_matrix_542 {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n=matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r<0 || r>=m || c<0 || c>=n || matrix[r][c] <= matrix[cell[0]][cell[1]]+1) {
                    continue;
                }
                queue.offer(new int[]{r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }
}
