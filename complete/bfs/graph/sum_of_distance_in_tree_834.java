package complete.bfs.graph;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 树中距离之和
 *
    给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。

    第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。

    返回一个表示节点 i 与其他所有节点距离之和的列表 ans。

    示例 1:

    输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
    输出: [8,12,6,10,10,10]
    解释:
    如下为给定的树的示意图：
        0
       / \
      1   2
         /|\
        3 4 5

    我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
    也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
    说明: 1 <= N <= 10000


 */
public class sum_of_distance_in_tree_834 {

    /**
     * dfs

     The first step is to calculate the size of each subtree and the sum distance for the root of each subtree. Also, the sum distance for our chosen root is the correct sum distance for the entire tree.
     The second step is to calculte the sum distance in the whole tree for each node, using the size of each subtree.

     sumDist(B) = sumDist(A)-cnt(B)+cnt(A)
     sumDist(B) = sumDist(A)+N-2*cnt(B)
     */
    private int[] res, count, ans;
    private int N;
    private List<List<Integer>> tree;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<>();
        this.N=N;
        res = new int[N];
        count = new int[N];
        ans = new int[N];
        for (int i=0; i<N; i++) tree.add(new ArrayList<>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        sumSubtreeDist(0, new HashSet<>());
        visit(0, -1, new HashSet<>());
        return ans;
    }
    private int[] sumSubtreeDist(int root, Set<Integer> set) {
        int cnt=0, tmp=0;
        set.add(root);
        for (int i : tree.get(root)) {
            if (set.contains(i)) continue;
            int[] preRes = sumSubtreeDist(i, set);
            cnt += preRes[0];
            tmp += preRes[0]+preRes[1];
        }
        count[root] = cnt+1;
        res[root] = tmp;
        return new int[]{cnt+1, tmp};
    }
    private void visit(int root, int pre, Set<Integer> set) {
        if (pre==-1) {
            ans[root] = res[root];
        } else {
            ans[root] = ans[pre]+N-2*count[root];
        }
        set.add(root);
        for (int i : tree.get(root)) {
            if (set.contains(i)) continue;
            visit(i, root, set);
        }
    }

    /**
     * bfs
     */
    public int[] sumOfDistancesInTree2(int N, int[][] edges) {
        Node[] list = new Node[N];
        for (int i=0; i<N; i++) list[i] = new Node(i);
        for (int[] e : edges) {
            list[e[0]].children.add(list[e[1]]);
            list[e[1]].children.add(list[e[0]]);
        }
        int[] ans = new int[N];
        for (int i=0; i<N; i++) {
            ans[i] = bfs(i, list);
        }
        return ans;
    }
    private int bfs(int index, Node[] list) {
        int res = 0, level=0;
        Queue<Node> q = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        set.add(list[index]);
        q.offer(list[index]);
        while (!q.isEmpty()) {
            int size = q.size();
            res += size*level;
            level++;
            for (int i=0; i<size; i ++) {
                Node cur = q.poll();
                for (Node n : cur.children) {
                    if (!set.contains(n)) {
                        q.offer(n);
                        set.add(n);
                    }
                }
            }
        }
        return res;
    }
    private class Node {
        int val;
        List<Node> children=new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }
}
