package complete.tree.bst_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 验证二叉搜索树
 *
    给定一个二叉树，判断其是否是一个有效的二叉搜索树。

    假设一个二叉搜索树具有如下特征：

    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。
    示例 1:

    输入:
      2
     / \
    1   3
    输出: true
    示例 2:

    输入:
      5
     / \
    1   4
       / \
      3   6
    输出: false
    解释: 输入为: [5,1,4,null,null,3,6]。
    根节点的值为 5 ，但是其右子节点值为 4 。

 */
public class validate_binary_search_tree_98 {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        int[] ans = helper(root);
        return ans[0]==1;
    }
    private int[] helper(TreeNode node) {
        if (node.left==null && node.right==null) {
            return new int[]{1, node.val, node.val};
        }
        int[] bad = new int[]{0, 0, 0};
        int ll, lr, rl, rr;
        if (node.left!=null) {
            int[] l = helper(node.left);
            if (l[0] == 0 || l[2]>=node.val) {
                return bad;
            } else {
                ll = l[1]; lr = l[2];
            }
        } else {
            ll = node.val;
        }
        if (node.right!=null) {
            int[] r = helper(node.right);
            if (r[0] == 0 || r[1]<=node.val) {
                return bad;
            } else {
                rl=r[1]; rr=r[2];
            }
        } else {
            rr = node.val;
        }
        return new int[]{1, ll, rr};
    }
}
