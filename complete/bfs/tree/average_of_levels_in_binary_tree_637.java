package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 二叉树的层平均值
 *
    给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.

    示例 1:

    输入:
      3
     / \
    9  20
      /  \
     15   7
    输出: [3, 14.5, 11]
    解释:
    第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
    注意：

    节点值的范围在32位有符号整数范围内。

 */
public class average_of_levels_in_binary_tree_637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len = 1;
        while (!queue.isEmpty()) {
            double avg = 0;
            int newlen = 0;
            for (int i=0; i<len; i++) {
                TreeNode node = queue.poll();
                avg += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                    newlen++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    newlen++;
                }
            }
            res.add(avg/len);
            avg = 0;
            len = newlen;
        }
        return res;
    }
}
