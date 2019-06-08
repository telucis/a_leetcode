package complete.tree.bst_easy;

import dataStruct.TreeNode;

import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 二叉搜索树中的众数
 *
    给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

    假定 BST 有如下定义：

    结点左子树中所含结点的值小于等于当前结点的值
    结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树
    例如：
    给定 BST [1,null,2,2],

    1
     \
      2
     /
    2
    返回[2].

    提示：如果众数超过1个，不需考虑输出顺序

    进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）


 */
public class find_mode_in_binary_search_tree_501 {
    private int maxCount = 0;
    private int count = 0;
    private Integer pre = null;
    private HashSet<Integer> res = new HashSet<>();
    public int[] findMode(TreeNode root) {
        helper(root);
        int[] result = new int[res.size()];
        int i=0;
        for (Integer item : res) {
            result[i] = item;
            i++;
        }
        return result;
    }
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (pre == null || pre != root.val) {
            pre = root.val;
            count = 1;
        } else if (root.val == pre) {
            count += 1;
        }
        if (maxCount == count) {
            res.add(root.val);
        } else if (maxCount < count) {
            res.clear();
            res.add(root.val);
            maxCount = count;
        }
        helper(root.right);
    }
}
