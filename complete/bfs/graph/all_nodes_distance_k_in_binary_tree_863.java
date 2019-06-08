package complete.bfs.graph;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 二叉树中所有距离为 K 的结点
 *
    给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

    返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。



    示例 1：

    输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

    输出：[7,4,1]

    解释：
    所求结点为与目标结点（值为 5）距离为 2 的结点，
    值分别为 7，4，以及 1



    注意，输入的 "root" 和 "target" 实际上是树上的结点。
    上面的输入仅仅是对这些对象进行了序列化描述。


    提示：

    给定的树是非空的，且最多有 K 个结点。
    树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
    目标结点 target 是树上的结点。
    0 <= K <= 1000.

 */
public class all_nodes_distance_k_in_binary_tree_863 {
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        if (root == null || K < 0) {
            return res;
        }
        buildMap(root, null);
        if (!map.containsKey(target)) {
            return res;
        }
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (K==0) {
                for (int i=0; i<size; i++) {
                    res.add(queue.poll().val);
                }
                return res;
            }
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode next : map.get(node)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    visited.add(next);
                    queue.add(next);
                }
            }
            K--;
        }
        return res;
    }
    private void buildMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<>());
            if (parent!= null) {
                map.get(node).add(parent);
                map.get(parent).add(node);
            }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }
}
