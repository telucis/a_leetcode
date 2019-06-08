package complete.bfs.hard;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 为高尔夫比赛砍树
 *
    你被请来给一个要举办高尔夫比赛的树林砍树. 树林由一个非负的二维数组表示， 在这个数组中：

    0 表示障碍，无法触碰到.
    1 表示可以行走的地面.
    比1大的数 表示一颗允许走过的树的高度.
    你被要求按照树的高度从低向高砍掉所有的树，每砍过一颗树，树的高度变为1。

    你将从（0，0）点开始工作，你应该返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。

    可以保证的是，没有两棵树的高度是相同的，并且至少有一颗树需要你砍。

    示例 1:

    输入:
    [
    [1,2,3],
    [0,0,4],
    [7,6,5]
    ]
    输出: 6


    示例 2:

    输入:
    [
    [1,2,3],
    [0,0,0],
    [7,6,5]
    ]
    输出: -1


    示例 3:

    输入:
    [
    [2,3,4],
    [0,0,5],
    [8,7,6]
    ]
    输出: 6

    解释: (0,0) 位置的树，你可以直接砍去，不用算步数


    提示: 矩阵大小不会超过 50x50 。


 */
public class cut_off_trees_for_golf_event_675 {
    static int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest==null || forest.size()==0) return 0;
        int m=forest.size(), n=forest.get(0).size();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[2]-b[2]);
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (forest.get(i).get(j)>1) {
                    pq.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[2];
        int sum=0;
        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            int step = minStep(forest, start, tree, m, n);

            if (step<0) return -1;
            sum += step;
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return sum;
    }
    private int minStep(List<List<Integer>> forest, int[] start, int[] tree, int m, int n) {
        int step = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int[] cur = queue.poll();
                if (cur[0]==tree[0] && cur[1]==tree[1]) return step;
                for (int[] d : dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n
                        || forest.get(nr).get(nc) == 0 || visited[nr][nc]) continue;
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
            step++;
        }
        return -1;
    }
}
