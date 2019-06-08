package complete.bfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 访问所有节点的最短路径
 *
    给出 graph 为有 N 个节点（编号为 0, 1, 2, ..., N-1）的无向连通图。

    graph.length = N，且只有节点 i 和 j 连通时，j != i 在列表 graph[i] 中恰好出现一次。

    返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。



    示例 1：

    输入：[[1,2,3],[0],[0],[0]]
    输出：4
    解释：一个可能的路径为 [1,0,2,0,3]
    示例 2：

    输入：[[1],[0,2,4],[1,3,4],[2],[1,2]]
    输出：4
    解释：一个可能的路径为 [0,1,4,2,3]


    提示：

    1 <= graph.length <= 12
    0 <= graph[i].length < graph.length

 */
public class shortest_path_visiting_all_nodes_847 {

    public int shortestPathLength(int[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int target = 0;
        for (int i=0; i<graph.length; i++) {
            int status = (1 << (i+1));
            target = target | (1<<(i+1));
            visited.add(status + ":" + i);
            q.offer(new int[]{status, i});
        }
        int level=0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k=0; k<size; k++) {
                int[] cur = q.poll();
                if (cur[0]==target) return level;
                for (int i=0; i<graph[cur[1]].length; i++) {
                    int nextNode = graph[cur[1]][i];
                    int nextStatus = cur[0] | (1<<(nextNode+1));
                    if (visited.contains(nextStatus+":"+nextNode)) continue;
                    visited.add(nextStatus+":"+nextNode);
                    q.offer(new int[]{nextStatus, nextNode});
                }
            }
            level++;
        }
        return -1;
    }
}
