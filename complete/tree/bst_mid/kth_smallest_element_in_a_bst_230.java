package complete.tree.bst_mid;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 二叉搜索树中第K小的元素
 *
    给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

    说明：
    你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

    示例 1:

    输入: root = [3,1,4,null,2], k = 1
    3
    / \
    1   4
    \
    2
    输出: 1
    示例 2:

    输入: root = [5,3,6,2,4,null,null,1], k = 3
    5
    / \
    3   6
    / \
    2   4
    /
    1
    输出: 3
    进阶：
    如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？


 */
public class kth_smallest_element_in_a_bst_230 {
    /**
     * 中序遍历
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = helper(root);
        return list.get(k-1);
    }
    private List<Integer> helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> left = helper(root.left);
        List<Integer> right = helper(root.right);
        List<Integer> res = new ArrayList<>();
        if (left != null) {
            res.addAll(left);
        }
        res.add(root.val);
        if (right != null) {
            res.addAll(right);
        }
        return res;
    }

    /**
     * follow up
     */
    public int kthSmallest2(TreeNode root, int k) {
        int left = nodeCount(root.left);
        if (left+1 == k) {
            return root.val;
        } else if (left+1<k) {
            return kthSmallest2(root.right, k-left-1);
        } else {
            return kthSmallest2(root.left, k);
        }
    }
    private int nodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1+nodeCount(root.left) + nodeCount(root.right);
    }
}
