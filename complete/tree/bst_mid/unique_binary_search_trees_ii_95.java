package complete.tree.bst_mid;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 不同的二叉搜索树 II
 *
    给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

    示例:

    输入: 3
    输出:
    [
    [1,null,3,2],
    [3,2,null,1],
    [3,1,null,null,2],
    [2,1,3],
    [1,null,2,null,3]
    ]
    解释:
    以上的输出对应以下 5 种不同结构的二叉搜索树：

    1         3     3      2      1
     \       /     /      / \      \
      3     2     1      1   3      2
     /     /       \                 \
    2     1         2                 3

 */
public class unique_binary_search_trees_ii_95 {
    public List<TreeNode> generateTrees(int n) {
        if (n==0) {
            return new ArrayList<>();
        }
        return helper(1, n);
    }
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start>end) {
            list.add(null);
            return list;
        }
        if (start==end) {
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> left, right;
        for (int i=start; i<=end; i++) {
            left = helper(start, i-1);
            right = helper(i+1, end);
            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
