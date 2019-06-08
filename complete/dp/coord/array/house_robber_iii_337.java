package complete.dp.coord.array;

import dataStruct.TreeNode;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 打家劫舍 III
 *
    在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

    计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

    示例 1:

    输入: [3,2,3,null,3,null,1]

      3
     / \
    2   3
     \   \
      3   1

    输出: 7
    解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
    示例 2:

    输入: [3,4,5,1,3,null,1]

        3
       / \
      4   5
     / \   \
    1   3   1

    输出: 9
    解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

 */
public class house_robber_iii_337 {

    /**
     * memo=map<treeNode, int>
     * ans=Math.max(rob(root.left)+rob(root.right), root.val+rob(r.left.left)+rob(r.left.right)+rob(r.right.left)+rob(r.right.right))
     */
    HashMap<TreeNode, Integer> cache = new HashMap<>();
    public int rob(TreeNode root) {
        int ans = 0;
        if (root==null) {
            return ans;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        if (root.left!=null) {
            ans += rob(root.left.left)+rob(root.left.right);
        }
        if (root.right!=null) {
            ans += rob(root.right.left)+rob(root.right.right);
        }
        ans = Math.max(ans+root.val, rob(root.left)+rob(root.right));
        cache.put(root, ans);
        return ans;
    }
}
