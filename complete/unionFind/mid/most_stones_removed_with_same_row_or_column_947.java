package complete.unionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 移除最多的同行或同列石头
 *
    在二维平面上，我们将石头放置在一些整数坐标点上。每个坐标点上最多只能有一块石头。

    现在，move 操作将会移除与网格上的某一块石头共享一列或一行的一块石头。

    我们最多能执行多少次 move 操作？



    示例 1：

    输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    输出：5
    示例 2：

    输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    输出：3
    示例 3：

    输入：stones = [[0,0]]
    输出：0


    提示：

    1 <= stones.length <= 1000
    0 <= stones[i][j] < 10000

 */
public class most_stones_removed_with_same_row_or_column_947 {
    int count = 0;

    public int removeStones(int[][] stones) {
        Map<String, String> parent = new HashMap<>();
        count = stones.length;
        for (int[] stone : stones) {
            String s = stone[0] + " " + stone[1];
            parent.put(s, s);
        }
        for (int[] s1 : stones) {
            String ss1 = s1[0] + " " + s1[1];
            for (int[] s2 : stones) {
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    String ss2 = s2[0] + " " + s2[1];
                    union(parent, ss1, ss2);
                }
            }
        }
        return stones.length - count;
    }

    private void union(Map<String, String> parent, String s1, String s2) {
        String r1 = find(parent, s1), r2 = find(parent, s2);
        if (r1.equals(r2)) {
            return;
        }
        parent.put(r1, r2);
        count--;
    }

    private String find(Map<String, String> parent, String s) {
        if (!parent.get(s).equals(s)) {
            parent.put(s, find(parent, parent.get(s)));
        }
        return parent.get(s);
    }

    /**
     * dfs
     */
    public int removeStones2(int[][] stones) {
        Set<int[]> visited = new HashSet<>();
        int numOfIslands = 0;
        for (int[] s1 : stones) {
            if (!visited.contains(s1)) {
                dfs(s1, visited, stones);
                numOfIslands++;
            }
        }
        return stones.length-numOfIslands;
    }
    private void dfs(int[] s1, Set<int[]> visited, int[][] stones) {
        visited.add(s1);
        for (int[] s2 : stones) {
            if (!visited.contains(s2)) {
                if (s1[0]==s2[0] || s1[1]==s2[1]) {
                    dfs(s2, visited, stones);
                }
            }
        }
    }
}