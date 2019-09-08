package complete.tree.pathProblem;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 路径总和
 *
    给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

    说明: 叶子节点是指没有子节点的节点。

    示例:
    给定如下二叉树，以及目标和 sum = 22，

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
    返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。


 */
public class path_sum_112 {
    private boolean hasSum = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        getSum(root, sum);
        return hasSum;
    }
    private void getSum(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            hasSum = true;
        }
        if (root.left != null) {
            getSum(root.left, sum-root.val);
        }
        if (root.right != null) {
            getSum(root.right, sum-root.val);
        }
    }


    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root==null) return false;
        if (root.val==sum && root.left==null && root.right==null) return true;
        if (root.left!=null &&hasPathSum2(root.left, sum-root.val)) return true;
        if (root.right!=null &&hasPathSum2(root.right, sum-root.val))
            return true;
        return false;
    }
}
