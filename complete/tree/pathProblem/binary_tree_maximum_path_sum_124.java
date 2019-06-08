package complete.tree.pathProblem;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 二叉树中的最大路径和
 *
    给定一个非空二叉树，返回其最大路径和。

    本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

    示例 1:

    输入: [1,2,3]

    1
    / \
    2   3

    输出: 6
    示例 2:

    输入: [-10,9,20,null,null,15,7]

    -10
    / \
    9  20
    /  \
    15   7

    输出: 42

 */
public class binary_tree_maximum_path_sum_124 {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        helper(root);
        return max;
    }
    private int helper(TreeNode root) {
        int ans=root.val;
        int left = root.left==null ? 0 : helper(root.left);
        if (left>0) ans+=left;
        int right = root.right==null ? 0 : helper(root.right);
        if (right>0) ans+=right;
        max = Math.max(max, ans);
        return Math.max(0, Math.max(left, right))+root.val;
    }
}
