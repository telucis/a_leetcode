package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 二叉树中第二小的节点
 *
    给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。

    给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

    示例 1:

    输入:
      2
     / \
    2   5
       / \
      5   7

    输出: 5
    说明: 最小的值是 2 ，第二小的值是 5 。
    示例 2:

    输入:
      2
     / \
    2   2

    输出: -1
    说明: 最小的值是 2, 但是不存在第二小的值。

 */
public class second_minimum_node_in_a_binary_tree_671 {
    private int value = -1;
    public int findSecondMinimumValue(TreeNode root) {
        helper(root);
        return value;
    }
    private void helper(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null) {
            return;
        }
        if (left.val != root.val) {
            if (value == -1) {
                value = left.val;
            } else {
                value = Math.min(left.val, value);
            }
        }
        if (right.val != root.val) {
            if (value == -1) {
                value = right.val;
            } else {
                value = Math.min(right.val, value);
            }
        }
        helper(root.left);
        helper(root.right);
    }
}
