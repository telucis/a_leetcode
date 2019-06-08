package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 左叶子之和
 *
    计算给定二叉树的所有左叶子之和。

    示例：

      3
     / \
    9  20
      /  \
    15   7

    在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class sum_of_left_leaves_404 {

    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }
    private int helper(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, true);
        int right = helper(root.right, false);
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return left + right;
    }
}
