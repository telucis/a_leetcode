package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 二叉树的层次遍历 II
 *
    给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

    例如：
    给定二叉树 [3,9,20,null,null,15,7],

      3
     / \
    9  20
      /  \
     15   7
    返回其自底向上的层次遍历为：

    [
    [15,7],
    [9,20],
    [3]
    ]

 */
public class binary_tree_level_order_traversal_ii_107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        List<Integer> tmpList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                tmpList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            } else {
                res.add(tmpList);
                tmpList = new ArrayList<>();
                if (!queue.isEmpty() || queue.peek() != null) {
                    queue.offer(null);
                }
            }
        }
        Collections.reverse(res);
        return res;
    }
}
