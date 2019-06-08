package complete.bfs.graph;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 可能的二分法
 *
    给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。

    每个人都可能不喜欢其他人，那么他们不应该属于同一组。

    形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。

    当可以用这种方法将每个人分进两组时，返回 true；否则返回 false。



    示例 1：

    输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
    输出：true
    解释：group1 [1,4], group2 [2,3]
    示例 2：

    输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
    输出：false
    示例 3：

    输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    输出：false


    提示：

    1 <= N <= 2000
    0 <= dislikes.length <= 10000
    1 <= dislikes[i][j] <= N
    dislikes[i][0] < dislikes[i][1]
    对于 dislikes[i] == dislikes[j] 不存在 i != j

 */
public class possible_bipartition_886 {
    /**
     * bfs graph
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] color = new int[N+1];
        List<List<Integer>> adj = new ArrayList<>(N+1);
        for (int i=0; i<=N; i++) adj.add(new ArrayList<>());
        for (int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }
        for (int i=1; i<=N; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int nb : adj.get(cur)) {
                        if (color[nb] == 0) {
                            color[nb] = color[cur] == 1 ? 2 : 1;
                            q.add(nb);
                        } else {
                            if (color[nb] == color[cur]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * dfs
     */
    public boolean possibleBipartition2(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] d : dislikes) {
            map.putIfAbsent(d[0], new HashSet<>());
            map.putIfAbsent(d[1], new HashSet<>());
            map.get(d[0]).add(d[1]);
            map.get(d[1]).add(d[0]);
        }
        return helper(map, 1, new HashSet<>(), new HashSet<>(), N);
    }
    private boolean helper(Map<Integer, Set<Integer>> map, int n, Set<Integer> s1, Set<Integer> s2, int N) {
        if (n>N) return true;
        if (s1.isEmpty() || !map.containsKey(n) || judge(map.get(n), s1)) {
            s1.add(n);
            if (helper(map, n+1, s1, s2, N)) return true;
            s1.remove(n);
        }
        if (s2.isEmpty() || !map.containsKey(n) || judge(map.get(n), s2)) {
            s2.add(n);
            if (helper(map, n+1, s1, s2, N)) return true;
            s2.remove(n);
        }
        return false;
    }
    private boolean judge(Set<Integer> dislike, Set<Integer> cand) {
        for (Integer c : cand) {
            if (dislike.contains(c)) return false;
        }
        return true;
    }
}
