package complete.twoPointer.reversePointer;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 两数之和 IV - 输入 BST
 *
    给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

    案例 1:

    输入:
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 9

    输出: True


    案例 2:

    输入:
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 28

    输出: False

 */
public class two_sum_iv_input_is_a_bst_653 {

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = helper(root);
        int l = 0, r = list.size()-1;
        while (l < r) {
            if (list.get(l) + list.get(r) == k) {
                return true;
            } else if (list.get(l)+list.get(r) > k) {
                r--;
            } else {
                l++;
            }
        }
        return false;
    }

    private List<Integer> helper(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> left = helper(root.left);
        List<Integer> right = helper(root.right);
        res.addAll(left);
        res.add(root.val);
        res.addAll(right);
        return res;
    }
}
