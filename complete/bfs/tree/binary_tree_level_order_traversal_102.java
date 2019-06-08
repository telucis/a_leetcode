package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 二叉树的层次遍历
 *
    给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

    例如:
    给定二叉树: [3,9,20,null,null,15,7],

    3
    / \
    9  20
    /  \
    15   7
    返回其层次遍历结果：

    [
    [3],
    [9,20],
    [15,7]
    ]

 */
public class binary_tree_level_order_traversal_102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
