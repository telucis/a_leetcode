package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 在每个树行中找最大值
 *
    您需要在二叉树的每一行中找到最大的值。

    示例：

    输入:

        1
       / \
      3   2
     / \   \
    5   3   9

    输出: [1, 3, 9]

 */
public class find_largest_value_in_each_tree_row_515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }
}