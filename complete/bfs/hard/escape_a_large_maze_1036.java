package complete.bfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 逃离大迷宫
 *
    在一个 10^6 x 10^6 的网格中，每个网格块的坐标为 (x, y)，其中 0 <= x, y < 10^6。

    我们从源方格 source 开始出发，意图赶往目标方格 target。每次移动，我们都可以走到网格中在四个方向上相邻的方格，只要该方格不在给出的封锁列表 blocked 上。

    只有在可以通过一系列的移动到达目标方格时才返回 true。否则，返回 false。



    示例 1：

    输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
    输出：false
    解释：
    从源方格无法到达目标方格，因为我们无法在网格中移动。
    示例 2：

    输入：blocked = [], source = [0,0], target = [999999,999999]
    输出：true
    解释：
    因为没有方格被封锁，所以一定可以到达目标方格。


    提示：

    0 <= blocked.length <= 200
    blocked[i].length == 2
    0 <= blocked[i][j] < 10^6
    source.length == target.length == 2
    0 <= source[i][j], target[i][j] < 10^6
    source != target

 */
public class escape_a_large_maze_1036 {
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int mod = 1000000;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return bfs(blocked, source, target) && bfs(blocked, target, source);
    }
    private boolean bfs(int[][] blocked, int[] source, int[] target) {
        if (blocked.length==0) return true;
        HashSet<String> block = new HashSet<>();
        for (int[] b : blocked) block.add(b[0]+" "+b[1]);
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(source);
        visited.add(source[0]+" "+source[1]);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int[] cur = queue.poll();
                if (cur[0]==target[0] && cur[1]==target[1]) return true;
                for (int i=0; i<4; i++) {
                    int x=cur[0]+dirs[i][0], y=cur[1]+dirs[i][1];
                    if (x>=0&&y>=0&&x<mod&&y<mod) {
                        String coord = cur[0]+" "+cur[1];
                        if(block.contains(coord) || visited.contains(coord)) continue;
                        visited.add(coord);
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            System.out.println(level);
            level++;
            if (level==blocked.length) return true;
        }
        return false;
    }
}
