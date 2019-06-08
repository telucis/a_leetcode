package complete.tree.pathProblem;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 二叉树的所有路径
 *
    给定一个二叉树，返回所有从根节点到叶子节点的路径。

    说明: 叶子节点是指没有子节点的节点。

    示例:

    输入:

       1
     /   \
    2     3
     \
      5

    输出: ["1->2->5", "1->3"]

    解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class binary_tree_paths_257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (int i=0; i<left.size(); i++) {
            paths.add(String.valueOf(root.val) + "->" + left.get(i));
        }
        for (int i=0; i<right.size(); i++) {
            paths.add(String.valueOf(root.val) + "->" + right.get(i));
        }
        if (left.size() == 0 && right.size() == 0) {
            paths.add(String.valueOf(root.val));
        }
        return paths;
    }
}
