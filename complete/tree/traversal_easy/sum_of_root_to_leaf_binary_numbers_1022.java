package complete.tree.traversal_easy;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 从根到叶的二进制数之和
 *
    给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。

    对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。

    以 10^9 + 7 为模，返回这些数字之和。



    示例：



    输入：[1,0,1,0,1,0,1]
    输出：22
    解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22


    提示：

    树中的结点数介于 1 和 1000 之间。
    node.val 为 0 或 1 。

 */
public class sum_of_root_to_leaf_binary_numbers_1022 {

    public int sumRootToLeaf(TreeNode root) {
        if (root==null) {
            return 0;
        }
        List<Integer> sum = new ArrayList<>();
        helper(root, sum, 0);
        int res = 0;
        for (int i=0; i<sum.size(); i++) {
            res += sum.get(i);
        }
        return res;
    }
    private void helper(TreeNode node, List<Integer> list, int pre) {
        int cur = pre*2+node.val;
        if (node.left==null && node.right==null) {
            list.add(cur);
        }
        if (node.left!=null) {
            helper(node.left, list, cur);
        }
        if (node.right!=null) {
            helper(node.right, list, cur);
        }
    }
}
