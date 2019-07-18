package complete.tree.pathProblem;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 二叉树的直径
 *
    给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

    示例 :
    给定二叉树

        1
       / \
      2   3
     / \
    4   5
    返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

    注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class diameter_of_binary_tree_543 {
    private int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return diameter;
    }
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int leftDep=0, rightDep=0;
        int res = 0;
        if (root.left != null) {
            leftDep = helper(root.left);
            res += leftDep + 1;
        }
        if (root.right != null) {
            rightDep = helper(root.right);
            res += rightDep + 1;
        }
        diameter = Math.max(diameter, res);
        return Math.max(leftDep, rightDep) + 1;
    }
}
