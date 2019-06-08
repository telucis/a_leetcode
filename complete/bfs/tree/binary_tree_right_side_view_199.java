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
 * 二叉树的右视图
 *
    给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

    示例:

    输入: [1,2,3,null,5,null,4]
    输出: [1, 3, 4]
    解释:

       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---

 */
public class binary_tree_right_side_view_199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        if (root == null) {
            return ans;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (i==size-1) {
                    ans.add(node.val);
                }
                if (node.left!=null) {
                    queue.offer(node.left);
                }
                if (node.right!=null) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }
}
