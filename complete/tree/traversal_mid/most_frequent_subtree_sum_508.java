package complete.tree.traversal_mid;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 出现次数最多的子树元素和
 *
    给出二叉树的根，找出出现次数最多的子树元素和。一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。然后求出出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）。



    示例 1
    输入:

    5
    /  \
    2   -3
    返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。

    示例 2
    输入:

    5
    /  \
    2   -5
    返回 [2]，只有 2 出现两次，-5 只出现 1 次。



    提示： 假设任意子树元素和均可以用 32 位有符号整数表示。


 */
public class most_frequent_subtree_sum_508 {
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<TreeNode, Integer> cache = new HashMap<>();
    int max = Integer.MIN_VALUE;
    public int[] findFrequentTreeSum(TreeNode root) {
        helper(root);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(a->a).toArray();
    }
    private int helper(TreeNode node) {
        if (node==null) {
            return 0;
        }
        if (cache.get(node) != null) {
            return cache.get(node);
        }
        int ans = node.val;
        if (node.left != null) {
            ans += helper(node.left);
        }
        if (node.right != null) {
            ans += helper(node.right);
        }
        cache.put(node, ans);
        map.put(ans, map.getOrDefault(ans, 0)+1);
        max = Math.max(max, map.get(ans));
        return ans;
    }
}
