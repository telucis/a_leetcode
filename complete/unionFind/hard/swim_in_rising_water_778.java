package complete.unionFind.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 水位上升的泳池中游泳
 *
    在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

    现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

    你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？

    示例 1:

    输入: [[0,2],[1,3]]
    输出: 3
    解释:
    时间为0时，你位于坐标方格的位置为 (0, 0)。
    此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

    等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
    示例2:

    输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
    输入: 16
    解释:
    0  1  2  3  4
    24 23 22 21  5
    12 13 14 15 16
    11 17 18 19 20
    10  9  8  7  6

    最终的路线用加粗进行了标记。
    我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
    提示:

    2 <= N <= 50.
    grid[i][j] 位于区间 [0, ..., N*N - 1] 内。

 */
public class swim_in_rising_water_778 {

    /**
     * uf
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        Uf uf = new Uf(n*n);
        int time=0;
        while (uf.find(0) != uf.find(n*n-1)) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (grid[i][j]>time) continue;
                    if (i<n-1 && grid[i+1][j]<=time) uf.union(i*n+j, (i+1)*n+j);
                    if (j<n-1 && grid[i][j+1]<=time) uf.union(i*n+j, i*n+j+1);
                }
            }
            time++;
        }
        return time-1;
    }
    private class Uf {
        int[] id;
        public Uf(int n) {
            id=new int[n];
            for (int i=0; i<n; i++) {
                id[i]=i;
            }
        }
        public int find(int i) {
            if (i!=id[i]) {
                id[i] = find(id[i]);
            }
            return id[i];
        }
        public void union(int a, int b) {
            int pa=find(a), pb=find(b);
            if (pa==pb)return;
            id[pa]=pb;
        }
    }

    /**
     * dfs
     */
    public int swimInWater2(int[][] grid) {
        int n = grid.length;
        Set<Integer> set = new HashSet<>();
        int time=0;
        while (!set.contains(n*n-1)) {
            set.clear();
            dfs(grid, 0, 0, set, time);
            time++;
        }
        return time-1;
    }
    private void dfs(int[][] grid, int i, int j, Set<Integer> set, int time) {
        int n = grid.length;
        if (i>=0 && i<n && j>=0 && j<n && !set.contains(i*n+j) && time>=grid[i][j]) {
            set.add(i*n+j);
            int[] dx = new int[]{1,-1,0,0};
            int[] dy = new int[]{0,0,1,-1};
            for (int k=0; k<4; k++) {
                int xx=i+dx[k], yy=j+dy[k];
                dfs(grid, xx, yy, set, time);
            }
        } else {
            return;
        }
    }
}
