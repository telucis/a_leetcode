package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉树的前序遍历
 *
    给定一个二叉树，返回它的 前序 遍历。

    示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [1,2,3]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？


 */
public class binary_tree_preorder_traversal_144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ans;
    }
}
