package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 最短的桥
 *
    在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）

    现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。

    返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）



    示例 1：

    输入：[[0,1],[1,0]]
    输出：1
    示例 2：

    输入：[[0,1,0],[0,0,0],[0,0,1]]
    输出：2
    示例 3：

    输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
    输出：1


    提示：

    1 <= A.length = A[0].length <= 100
    A[i][j] == 0 或 A[i][j] == 1

 */
public class shortest_bridge_934 {

    public int shortestBridge(int[][] A) {
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{{-1,0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        boolean found = false;
        // dfs to find an island
        for (int i=0; i<m; i++) {
            if (found) {
                break;
            }
            for (int j=0; j<n; j++) {
                if (A[i][j] == 1) {
                    dfs(A, visited, q, i, j, dirs);
                    found = true;
                    break;
                }
            }
        }
        // bfs to expand this island
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- >0) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int i=cur[0]+dir[0];
                    int j=cur[1]+dir[1];
                    if (i>=0 && j>=0 && i<m && j<n && !visited[i][j]) {
                        if (A[i][j]==1) {
                            return step;
                        }
                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private void dfs(int[][] A, boolean[][] visited, Queue<int[]> q, int i, int j, int[][] dirs) {
        if (i<0 || j<0 || i>=A.length || j>=A[0].length || visited[i][j] || A[i][j]==0) {
            return;
        }
        visited[i][j] = true;
        q.offer(new int[]{i, j});
        for (int[] dir : dirs) {
            dfs(A, visited, q, i+dir[0], j+dir[1], dirs);
        }
    }
}
