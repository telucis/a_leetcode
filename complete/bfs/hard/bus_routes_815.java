package complete.bfs.hard;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 公交路线
 *
    我们有一系列公交路线。每一条路线 routes[i] 上都有一辆公交车在上面循环行驶。例如，有一条路线 routes[0] = [1, 5, 7]，表示第一辆 (下标为0) 公交车会一直按照 1->5->7->1->5->7->1->... 的车站路线行驶。

    假设我们从 S 车站开始（初始时不在公交车上），要去往 T 站。 期间仅可乘坐公交车，求出最少乘坐的公交车数量。返回 -1 表示不可能到达终点车站。

    示例:
    输入:
    routes = [[1, 2, 7], [3, 6, 7]]
    S = 1
    T = 6
    输出: 2
    解释:
    最优策略是先乘坐第一辆公交车到达车站 7, 然后换乘第二辆公交车到车站 6。
    说明:

    1 <= routes.length <= 500.
    1 <= routes[i].length <= 500.
    0 <= routes[i][j] < 10 ^ 6.

 */
public class bus_routes_815 {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S==T) return 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<routes.length; i++) {
            for (int j=0; j<routes[i].length; j++) {
                List<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                map.put(routes[i][j], buses);
            }
        }
        int ans = 0;
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        while (!q.isEmpty()) {
            int len = q.size();
            ans++;
            for (int i=0; i<len; i++) {
                int cur = q.poll();
                List<Integer> buses = map.get(cur);
                for (int bus : buses) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int j=0; j<routes[bus].length; j++) {
                        if (routes[bus][j]==T) return ans;
                        q.offer(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
    }
}
