package complete.tree.bst_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 二叉搜索树的范围和
 *
    给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

    二叉搜索树保证具有唯一的值。



    示例 1：

    输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
    输出：32
    示例 2：

    输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
    输出：23


    提示：

    树中的结点数量最多为 10000 个。
    最终的答案保证小于 2^31。

 */
public class range_sum_of_bst_938 {

    public int rangeSumBST(TreeNode root, int L, int R) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (root.val >= R) {
            int add = 0;
            if (root.val == R) {
                add = root.val;
            }
            return rangeSumBST(root.left, L, R) + add;
        } else if (root.val <= L) {
            int add = 0;
            if (root.val == L) {
                add = root.val;
            }
            return rangeSumBST(root.right, L, R) + add;
        }
        return rangeSumBST(root.left, L, root.val) + rangeSumBST(root.right, root.val, R) + root.val;
    }
}
