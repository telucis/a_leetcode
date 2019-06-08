package complete.tree.pathProblem;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 最长同值路径
 *
    给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

    注意：两个节点之间的路径长度由它们之间的边数表示。

    示例 1:

    输入:

        5
       / \
      4   5
     / \   \
    1   1   5
    输出:

    2
    示例 2:

    输入:

        1
       / \
      4   5
     / \   \
    4   4   5
    输出:

    2
    注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。


 */
public class     longest_univalue_path_687 {
    private int longestValue = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return longestValue;
        }
        getSinglePath(root);
        longestUnivaluePath(root.left);
        longestUnivaluePath(root.right);
        return longestValue;
    }
    private int getSinglePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        int left = 0, right = 0;
        if (root.left != null && root.left.val == root.val) {
            left = getSinglePath(root.left);
            sum += left;
        }
        if (root.right != null && root.right.val == root.val) {
            right = getSinglePath(root.right);
            sum += right;
        }
        longestValue = Math.max(longestValue, sum);
        return Math.max(left, right) + 1;
    }
}
