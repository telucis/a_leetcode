package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 被围绕的区域
 *
    给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

    找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

    示例:

    X X X X
    X O O X
    X X O X
    X O X X
    运行你的函数后，矩阵变为：

    X X X X
    X X X X
    X X X X
    X O X X
    解释:

    被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。


 */
public class surrounded_regions_130 {

    public void solve(char[][] board) {
        if (board.length==0) {
            return;
        }
        int m=board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i=0; i<m; i++) {
            if (board[i][0] =='O') {
                board[i][0] = '+';
                q.offer(new int[]{i, 0});
            }
            if (board[i][n-1] == 'O') {
                board[i][n-1] = '+';
                q.offer(new int[]{i, m-1});
            }
        }
        for (int i=0; i<n; i++) {
            if (board[0][i] =='O') {
                board[0][i] = '+';
                q.offer(new int[]{0, i});
            }
            if (board[m-1][i] == 'O') {
                board[m-1][i] = '+';
                q.offer(new int[]{m-1, i});
            }
        }
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i=0; i<4; i++) {
                int x = cur[0]+dx[i];
                int y = cur[1]+dy[i];
                if (x>=0 && y>=0 && x<m && y<n && board[x][y]=='O') {
                    board[x][y] = '+';
                    q.offer(new int[]{x, y});
                }
            }
        }
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '+') {
                    System.out.println(i + " " + j);
                    board[i][j] = 'O';
                }
            }
        }
    }
}
