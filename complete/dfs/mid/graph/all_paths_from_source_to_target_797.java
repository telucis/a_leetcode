package complete.dfs.mid.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 所有可能的路径
 *
    给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）

    二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了a→b你就不能从b→a）空就是没有下一个结点了。

    示例:
    输入: [[1,2], [3], [3], []]
    输出: [[0,1,3],[0,2,3]]
    解释: 图是这样的:
    0--->1
    |    |
    v    v
    2--->3
    这有两条路: 0 -> 1 -> 3 和 0 -> 2 -> 3.
    提示:

    结点的数量会在范围 [2, 15] 内。
    你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。

 */
public class all_paths_from_source_to_target_797 {

    /**
     * permutations
     * helper(graph, ans, path, graph[val][i])
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(graph, path, ans, 0);
        return ans;
    }
    private void helper(int[][] graph, List<Integer> path, List<List<Integer>> ans, int val) {
        path.add(val);
        if (val == graph.length-1) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int i=0; i<graph[val].length; i++) {
                helper(graph, path, ans, graph[val][i]);
            }
        }
        path.remove(path.size()-1);
    }
}
