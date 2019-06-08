package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 恢复二叉搜索树
 *
    二叉搜索树中的两个节点被错误地交换。

    请在不改变其结构的情况下，恢复这棵树。

    示例 1:

    输入: [1,3,null,null,2]

      1
     /
    3
     \
      2

    输出: [3,1,null,null,2]

      3
     /
    1
     \
      2
    示例 2:

    输入: [3,1,4,null,null,2]

      3
     / \
    1   4
       /
      2

    输出: [2,1,4,null,null,3]

      2
     / \
    1   4
       /
      3
    进阶:

    使用 O(n) 空间复杂度的解法很容易实现。
    你能想出一个只使用常数空间的解决方案吗？

 */
public class recover_binary_search_tree_99 {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        traverse(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    private void traverse(TreeNode root) {
        if (root==null) return;
        traverse(root.left);
        if (first==null && pre.val >= root.val) first = pre;
        if (first!=null && pre.val >= root.val) second = root;
        pre = root;
        traverse(root.right);
    }
}
