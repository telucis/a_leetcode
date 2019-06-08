package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 对称二叉树
 *
    给定一个二叉树，检查它是否是镜像对称的。

    例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

        1
       / \
      2   2
     / \ / \
    3  4 4  3
    但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

      1
     / \
    2   2
     \   \
     3    3
    说明:

    如果你可以运用递归和迭代两种方法解决这个问题，会很加分。


 */
public class symmetric_tree_101 {

    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }

    private boolean helper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        } else {
            if (node1.val == node2.val) {
                return helper(node1.left, node2.right) && helper(node1.right, node2.left);
            } else {
                return false;
            }
        }
    }
}
