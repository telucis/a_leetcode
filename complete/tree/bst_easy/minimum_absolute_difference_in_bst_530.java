package complete.tree.bst_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 二叉搜索树的最小绝对差
 *
    给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。

    示例 :

    输入:

    1
     \
      3
     /
    2

    输出:
    1

    解释:
    最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
    注意: 树中至少有2个节点。


 */
public class minimum_absolute_difference_in_bst_530 {
    private int min = Integer.MAX_VALUE;
    private Integer pre = null;
    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return min;
    }
    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        if (pre != null) {
            min = Math.min(min, node.val - pre);
        }
        pre = node.val;

        helper(node.right);
    }
}
