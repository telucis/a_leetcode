package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 太平洋大西洋水流问题
 *
    给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。

    规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。

    请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。



    提示：

    输出坐标的顺序不重要
    m 和 n 都小于150


    示例：



    给定下面的 5x5 矩阵:

 太平洋 ~   ~   ~   ~   ~
    ~  1   2   2   3  (5) *
    ~  3   2   3  (4) (4) *
    ~  2   4  (5)  3   1  *
    ~ (6) (7)  1   4   5  *
    ~ (5)  1   1   2   4  *
       *   *   *   *   * 大西洋

    返回:

    [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).

 */
public class pacific_atlantic_water_flow_417 {
    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for (int i=0; i<m; i++) {
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for (int i=0; i<n; i++) {
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{m-1, i});
            pacific[0][i] = true;
            atlantic[m-1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i,j});
                }
            }
        }
        return res;
    }
    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int n = matrix.length, m = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d:dir) {
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
