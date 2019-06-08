package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 二叉树的后序遍历
 *
    给定一个二叉树，返回它的 后序 遍历。

    示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [3,2,1]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？


 */
public class binary_tree_postorder_traversal_145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode cur, pre=null;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (pre==null || pre.left==cur || pre.right==cur) {
                if (cur.left!=null) stack.add(cur.left);
                else if (cur.right!=null) stack.add(cur.right);
            } else if (cur.left==pre) {
                if (cur.right!=null) stack.add(cur.right);
            } else {
                ans.add(stack.pop().val);
            }
            pre = cur;
        }
        return ans;
    }
}
