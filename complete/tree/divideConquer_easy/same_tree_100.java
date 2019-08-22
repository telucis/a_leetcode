package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 相同的树
 *
    给定两个二叉树，编写一个函数来检验它们是否相同。

    如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

    示例 1:

    输入:       1         1
              / \       / \
             2   3     2   3

            [1,2,3],   [1,2,3]

    输出: true
    示例 2:

    输入:      1          1
              /           \
             2             2

            [1,2],     [1,null,2]

    输出: false
    示例 3:

    输入:       1         1
              / \       / \
             2   1     1   2

            [1,2,1],   [1,1,2]

    输出: false

 */
public class same_tree_100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else {
            if (p.val != q.val) return false;
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        }
    }
}
