package complete.tree.divideConquer_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉树展开为链表
 *
    给定一个二叉树，原地将它展开为链表。

    例如，给定二叉树

        1
       / \
      2   5
     / \   \
    3   4   6
    将其展开为：

    1
    \
    2
    \
    3
    \
    4
    \
    5
    \
    6

 */
public class flatten_binary_tree_to_linked_list_114 {

    public void flatten(TreeNode root) {
        helper(root);
    }
    private TreeNode helper(TreeNode root) {
        if (root==null) {
            return null;
        }
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        root.left = null;
        if (left!=null) {
            root.right = left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
        } else {
            root.right = right;
        }
        return root;
    }
}
