package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉树的中序遍历
 *
    给定一个二叉树，返回它的中序 遍历。

    示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [1,3,2]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？


 */
public class binary_tree_inorder_traversal_94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            while (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }
}
