package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 找树左下角的值
 *
    给定一个二叉树，在树的最后一行找到最左边的值。

    示例 1:

    输入:

      2
     / \
    1   3

    输出:
    1


    示例 2:

    输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

    输出:
    7


    注意: 您可以假设树（即给定的根节点）不为 NULL。


 */
public class find_bottom_left_tree_value_513 {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root.left != null) {
                queue.offer(root.left);
            }
        }
        return root.val;
    }
}
