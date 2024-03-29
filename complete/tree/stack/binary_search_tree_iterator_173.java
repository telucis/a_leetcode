package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉搜索树迭代器
 *
    实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

    调用 next() 将返回二叉搜索树中的下一个最小的数。



    示例：



    BSTIterator iterator = new BSTIterator(root);
    iterator.next();    // 返回 3
    iterator.next();    // 返回 7
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 9
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 15
    iterator.hasNext(); // 返回 true
    iterator.next();    // 返回 20
    iterator.hasNext(); // 返回 false


    提示：

    next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
    你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。

 */
public class binary_search_tree_iterator_173 {
    class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur;
        public BSTIterator(TreeNode root) {
            cur = root;
            while (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode cur = stack.pop();
            int ans = cur.val;
            cur = cur.right;
            while (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            }
            return ans;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
