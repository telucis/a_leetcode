package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 叶子相似的树
 *
    请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。


    举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。

    如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

    如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

    提示：

    给定的两颗树可能会有 1 到 100 个结点。

 */
public class leaf_similar_trees_872 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafs1 = helper(root1);
        List<Integer> leafs2 = helper(root2);
        System.out.println(leafs1);
        if (leafs1.size() != leafs2.size()) {
            return false;
        }
        for (int i=0; i<leafs1.size(); i++) {
            if (leafs1.get(i).equals(leafs2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> helper(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> left = helper(root.left);
        List<Integer> right = helper(root.right);
        res.addAll(left);
        res.addAll(right);
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        return res;
    }
}
