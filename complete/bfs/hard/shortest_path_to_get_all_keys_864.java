package complete.bfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 获取所有钥匙的最短路径
 *
    给定一个二维网格 grid。 "." 代表一个空房间， "#" 代表一堵墙， "@" 是起点，（"a", "b", ...）代表钥匙，（"A", "B", ...）代表锁。

    我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。

    假设 K 为钥匙/锁的个数，且满足 1 <= K <= 6，字母表中的前 K 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。

    返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。



    示例 1：

    输入：["@.a.#","###.#","b.A.B"]
    输出：8
    示例 2：

    输入：["@..aA","..B#.","....b"]
    输出：6


    提示：

    1 <= grid.length <= 30
    1 <= grid[0].length <= 30
    grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
    钥匙的数目范围是 [1, 6]，每个钥匙都对应一个不同的字母，正好打开一个对应的锁。

 */
public class shortest_path_to_get_all_keys_864 {
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length(), max=0;
        int[] start=new int[3];
        for (int i=0; i<grid.length; i++) {
            String str = grid[i];
            for (int j=0; j<str.length(); j++) {
                char c = str.charAt(j);
                if (c=='@') {
                    start[0]=0;
                    start[1]=i;
                    start[2]=j;
                } else if (c>='a' && c<='f') {
                    max++;
                }
            }
        }
        if (max==0) return 0;
        Queue<int[]> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(start);
        set.add(start[0]+" "+start[1]+" "+start[2]);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                int[] cur = q.poll();
                if (cur[0]==(1<<max)-1) return ans;
                for (int j=0; j<4; j++) {
                    int x = cur[1]+dirs[j][0], y = cur[2]+dirs[j][1], key=cur[0];
                    if (x>=0 && x<m && y>=0 && y<n) {
                        char c = grid[x].charAt(y);
                        if (c=='#') continue;
                        if (c>='a' && c<='f') key |= 1<<(c-'a');
                        if (c>='A' && c<='F' && ((key>>(c-'A'))&1)==0) continue;
                        if (!set.contains(key+" "+x+" "+y)) {
                            set.add(key+" "+x+" "+y);
                            q.offer(new int[]{key, x, y});
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
