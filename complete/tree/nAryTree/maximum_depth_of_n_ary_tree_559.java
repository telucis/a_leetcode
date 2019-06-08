package complete.tree.nAryTree;

import dataStruct.Node;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * N叉树的最大深度
 *
    给定一个 N 叉树，找到其最大深度。

    最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

    例如，给定一个 3叉树 :


    我们应返回其最大深度，3。

    说明:

    树的深度不会超过 1000。
    树的节点总不会超过 5000。

 */
public class maximum_depth_of_n_ary_tree_559 {

    public int maxDepth(Node root) {
        int dep = 0;
        if (root == null) {
            return dep;
        }
        if (root.children != null) {
            for (int i=0; i<root.children.size(); i++) {
                dep = Math.max(dep, maxDepth(root.children.get(i)));
            }
        }
        return dep + 1;
    }
}
