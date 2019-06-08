package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 二叉树的锯齿形层次遍历
 *

    给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

    例如：
    给定二叉树 [3,9,20,null,null,15,7],

    3
    / \
    9  20
    /  \
    15   7
    返回锯齿形层次遍历如下：

    [
    [3],
    [20,9],
    [15,7]
    ]

 */
public class binary_tree_zigzag_level_order_traversal_103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left!=null) {
                    q.offer(node.left);
                }
                if (node.right!=null) {
                    q.offer(node.right);
                }
            }
            if (cnt%2==1) {
                Collections.reverse(list);
            }
            ans.add(list);
            cnt++;
        }
        return ans;
    }
}
