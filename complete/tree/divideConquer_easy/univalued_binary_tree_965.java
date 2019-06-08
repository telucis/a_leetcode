package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 单值二叉树
 *
    如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

    只有给定的树是单值二叉树时，才返回 true；否则返回 false。



    示例 1：



    输入：[1,1,1,1,1,null,1]
    输出：true
    示例 2：



    输入：[2,2,2,5,2]
    输出：false


    提示：

    给定树的节点数范围是 [1, 100]。
    每个节点的值都是整数，范围为 [0, 99] 。

 */
public class univalued_binary_tree_965 {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        if (!isUnivalTree(root.left)) {
            return false;
        }
        if (!isUnivalTree(root.right)) {
            return false;
        }
        return true;
    }
}
