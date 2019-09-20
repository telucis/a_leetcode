package complete.unionFind;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 冗余连接 II
 *
    在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。

    输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

    结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u and v和顶点的边，其中父节点u是子节点v的一个父节点。

    返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

    示例 1:

    输入: [[1,2], [1,3], [2,3]]
    输出: [2,3]
    解释: 给定的有向图如下:
      1
     / \
    v   v
    2-->3
    示例 2:

    输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
    输出: [4,1]
    解释: 给定的有向图如下:
    5 <- 1 -> 2
         ^    |
         |    v
         4 <- 3
    注意:

    二维数组大小的在3到1000范围内。
    二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。

 */
public class redundant_connection_ii_685 {

    /**
     * unionFind
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] roots = new int[edges.length];
        for (int i=0; i<edges.length; i++) roots[i]=i;
        int[] cand1=null, cand2=null;
        for (int[] e : edges) {
            int rootx = find(roots, e[0]-1), rooty = find(roots, e[1]-1);
            if (rootx != rooty) {
                if (rooty != e[1]-1) cand1=e;
                else roots[rooty] = rootx;
            } else cand2 = e;
        }
        if (cand1==null) return cand2;
        if (cand2==null) return cand1;
        for (int[] e : edges) if (e[1]==cand1[1]) return e;
        return new int[2];
    }
    private int find(int[] roots, int i) {
        while (i!=roots[i]) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }

    /**
     * bfs
     */
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        HashMap<Integer, Integer> indegree = new HashMap<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            map.putIfAbsent(e[0], new ArrayList<>());
            map.get(e[0]).add(e[1]);
            indegree.put(e[1], indegree.getOrDefault(e[1], 0)+1);
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i=1; i<=edges.length; i++) {
            if (indegree.get(i)==null) {
                q.offer(i);
                visited.add(i);
            }
        }
        if (q.isEmpty()) {
            for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
                if (m.getValue().contains(1)) {
                    return new int[]{m.getKey(),1};
                }
            }
        }
        while (!q.isEmpty()) {
            Integer v = q.poll();
            System.out.println(v);
            if (map.containsKey(v)) {
                for (Integer i : map.get(v)) {
                    if (visited.contains(i)) {
                        return new int[]{v, i};
                    } else {
                        q.offer(i);
                        visited.add(i);
                    }
                }
            }
        }
        return new int[2];
    }
}

