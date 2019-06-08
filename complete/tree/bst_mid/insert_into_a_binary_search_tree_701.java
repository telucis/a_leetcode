package complete.tree.bst_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉搜索树中的插入操作
 *
    给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

    注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

    例如,

    给定二叉搜索树:

    4
    / \
    2   7
    / \
    1   3

    和 插入的值: 5
    你可以返回这个二叉搜索树:

    4
    /   \
    2     7
    / \   /
    1   3 5
    或者这个树也是有效的:

    5
    /   \
    2     7
    / \
    1   3
    \
    4

 */
public class insert_into_a_binary_search_tree_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root==null) {
            return node;
        }
        if (root.val > val) {
            if (root.left==null) {
                root.left = node;
            } else {
                insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                insertIntoBST(root.right, val);
            }
        }
        return root;
    }
}
