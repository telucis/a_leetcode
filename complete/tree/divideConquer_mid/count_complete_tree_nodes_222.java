package complete.tree.divideConquer_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 完全二叉树的节点个数
 *
    给出一个完全二叉树，求出该树的节点个数。

    说明：

    完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

    示例:

    输入:
        1
       / \
      2   3
     / \  /
    4  5 6

    输出: 6

 */
public class count_complete_tree_nodes_222 {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left+right+1;
    }

    public int countNodes2(TreeNode root) {
        if (root==null) return 0;
        int ans = 1;
        int lh = getH(root.left), rh = getH(root.right);
        if (lh==rh) ans += Math.pow(2, lh)-1+countNodes2(root.right);
        else ans+=Math.pow(2, rh)-1+countNodes2(root.left);
        return ans;
    }
    private int getH(TreeNode root) {
        if (root==null) return 0;
        return getH(root.left)+1;
    }
}
