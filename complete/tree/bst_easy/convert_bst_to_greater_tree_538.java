package complete.tree.bst_easy;

import dataStruct.TreeNode;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 把二叉搜索树转换为累加树
 *
    给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

    例如：

    输入: 二叉搜索树:
       5
     /   \
    2     13

    输出: 转换为累加树:
        18
      /   \
    20     13

 */
public class convert_bst_to_greater_tree_538 {
    private int preNum = 0;
    public TreeNode convertBST(TreeNode root) {
        unPreOrder(root);
        return root;
    }
    private void unPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        unPreOrder(root.right);
        root.val += preNum;
        preNum = root.val;
        unPreOrder(root.left);
    }

    /**
     * 非递归
     */
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.right;
            }
            node = stack.pop();
            node.val += preNum;
            preNum = node.val;
            if (node.left != null) {
                node = node.left;
            } else {
                node = null;
            }
        }
        return root;
    }
}
