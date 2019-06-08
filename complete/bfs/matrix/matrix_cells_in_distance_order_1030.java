package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * 距离顺序排列矩阵单元格
 *
    给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。

    另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。

    返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）



    示例 1：

    输入：R = 1, C = 2, r0 = 0, c0 = 0
    输出：[[0,0],[0,1]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
    示例 2：

    输入：R = 2, C = 2, r0 = 0, c0 = 1
    输出：[[0,1],[0,0],[1,1],[1,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
    [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
    示例 3：

    输入：R = 2, C = 3, r0 = 1, c0 = 2
    输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
    其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。


    提示：

    1 <= R <= 100
    1 <= C <= 100
    0 <= r0 < R
    0 <= c0 < C

 */
public class matrix_cells_in_distance_order_1030 {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        visited[r0][c0] = true;
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int index = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            res[index] = node;
            for (int i=0; i<4; i++) {
                int xx = node[0] + dx[i];
                int yy = node[1] + dy[i];
                if (judge(R, C, visited, xx, yy)) {
                    visited[xx][yy] = true;
                    queue.offer(new int[]{xx, yy});
                }
            }
            index++;
        }
        return res;
    }
    private boolean judge(int R, int C, boolean[][] visited, int x, int y) {
        if (x>=0 && x<R && y>=0 && y<C && visited[x][y]==false) {
            return true;
        }
        return false;
    }
}
