package complete.tree.divideConquer_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 节点与其祖先之间的最大差值
 *
    给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

    （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）



    示例：



    输入：[8,3,10,1,6,null,14,null,null,4,7,13]
    输出：7
    解释：
    我们有大量的节点与其祖先的差值，其中一些如下：
    |8 - 3| = 5
    |3 - 7| = 4
    |8 - 1| = 7
    |10 - 13| = 3
    在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。


    提示：

    树中的节点数在 2 到 5000 之间。
    每个节点的值介于 0 到 100000 之间。

 */
public class maximum_difference_between_node_and_ancestor_1026 {

    public int maxAncestorDiff(TreeNode root) {
        if (root==null) {
            return 0;
        }
        int[] ans = helper(root);
        return ans[0];
    }
    private int[] helper(TreeNode node) {
        if (node.left==null && node.right==null) {
            return new int[]{0, node.val, node.val};
        }
        int min = node.val;
        int max = node.val;
        int ans = 0;
        if (node.left != null) {
            int[] left = helper(node.left);
            min = Math.min(min, left[1]);
            max = Math.max(max, left[2]);
            ans = Math.max(ans, left[0]);
        }
        if (node.right != null) {
            int[] right = helper(node.right);
            min = Math.min(min, right[1]);
            max = Math.max(max, right[2]);
            ans = Math.max(ans, right[0]);
        }
        return new int[]{Math.max(ans, Math.max(Math.abs(node.val-min), Math.abs(node.val-max))), min, max};
    }
}
