package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 腐烂的橘子
 *
    在给定的网格中，每个单元格可以有以下三个值之一：

    值 0 代表空单元格；
    值 1 代表新鲜橘子；
    值 2 代表腐烂的橘子。
    每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

    返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

    示例 1：

    输入：[[2,1,1],[1,1,0],[0,1,1]]
    输出：4
    示例 2：

    输入：[[2,1,1],[0,1,1],[1,0,1]]
    输出：-1
    解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
    示例 3：

    输入：[[0,2]]
    输出：0
    解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。


    提示：

    1 <= grid.length <= 10
    1 <= grid[0].length <= 10
    grid[i][j] 仅为 0、1 或 2

 */
public class rotting_oranges_994 {

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        Queue<Node> queue = new LinkedList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Node(i, j));
                }
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            boolean effect = false;
            for (int k=0; k<len; k++) {
                Node node = queue.poll();
                for (int i=0; i<4; i++) {
                    int x = dx[i] + node.x;
                    int y = dy[i] + node.y;
                    if (judgeNode(grid, x, y)) {
                        effect = true;
                        grid[x][y] = 2;
                        queue.offer(new Node(x, y));
                    }
                }
            }
            if (effect) {
                res++;
            }
        }
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[m][n] == 1) {
                    return -1;
                }
            }
        }
        return res;
    }
    private boolean judgeNode(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x >=0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
            return true;
        }
        return false;
    }
    class Node {
        public int x;
        public int y;
        public Node(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }
}
