package complete.bfs.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 网络延迟时间
 *
    有 N 个网络节点，标记为 1 到 N。

    给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。

    现在，我们向当前的节点 K 发送了一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。

    注意:

    N 的范围在 [1, 100] 之间。
    K 的范围在 [1, N] 之间。
    times 的长度在 [1, 6000] 之间。
    所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。

 */
public class netword_delay_time_743 {

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b)-> (a[0]-b[0]));
        pq.add(new int[]{0, K});
        boolean[] visited = new boolean[N+1];
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[1];
            int curTime = cur[0];
            if (visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            res = curTime;
            N--;
            if (map.containsKey(curNode)) {
                for (int next : map.get(curNode).keySet()) {
                    pq.add(new int[]{curTime+map.get(curNode).get(next), next});
                }
            }
        }
        return N==0 ? res : -1;
    }
}
