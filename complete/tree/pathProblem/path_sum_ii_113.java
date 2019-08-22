package complete.tree.pathProblem;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 路径总和 II
 *
    给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

    说明: 叶子节点是指没有子节点的节点。

    示例:
    给定如下二叉树，以及目标和 sum = 22，

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1
    返回:

    [
    [5,4,11,2],
    [5,8,4,5]
    ]

 */
public class path_sum_ii_113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        helper(root, sum, new ArrayList<>(), ans);
        return ans;
    }
    private void helper(TreeNode node, int sum, List<Integer> tmp, List<List<Integer>> ans) {
        if (node.left==null && node.right==null && sum==node.val) {
            tmp.add(node.val);
            ans.add(tmp);
            return;
        }
        if (node.left != null) {
            List<Integer> tmp1 = new ArrayList<>(tmp);
            tmp1.add(node.val);
            helper(node.left, sum-node.val, tmp1, ans);
        }
        if (node.right != null) {
            List<Integer> tmp2 = new ArrayList<>(tmp);
            tmp2.add(node.val);
            helper(node.right, sum-node.val, tmp2, ans);
        }
    }
}
