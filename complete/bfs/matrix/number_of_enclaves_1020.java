package complete.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 飞地的数量
 *
    给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。

    移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。

    返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。



    示例 1：

    输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
    输出：3
    解释：
    有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
    示例 2：

    输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
    输出：0
    解释：
    所有 1 都在边界上或可以到达边界。


    提示：

    1 <= A.length <= 500
    1 <= A[i].length <= 500
    0 <= A[i][j] <= 1
    所有行的大小都相同

 */
public class number_of_enclaves_1020 {

    public int numEnclaves(int[][] A) {
        if (A.length==0 || A[0].length==0) return 0;
        int m=A.length, n=A[0].length;
        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if ((i==0 || j==0 || i==m-1 || j==n-1) && A[i][j]==1) {
                    q.offer(i*n+j);
                    A[i][j]=0;
                }
            }
        }
        int[] dx=  new int[]{0,0,-1,1};
        int[] dy=  new int[]{1,-1,0,0};
        while (!q.isEmpty()) {
            int coord = q.poll();
            int x=coord/n, y=coord%n;
            for (int i=0; i<4; i++) {
                int xx = x+dx[i], yy=y+dy[i];
                if (xx>=0 && xx<m && yy>=0 && yy<n && A[xx][yy]==1) {
                    A[xx][yy]=0;
                    q.offer(xx*n+yy);
                }
            }
        }
        int ans = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (A[i][j]==1) ans++;
            }
        }
        return ans;
    }
}
