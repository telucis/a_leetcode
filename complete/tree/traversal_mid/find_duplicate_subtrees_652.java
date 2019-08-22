package complete.tree.traversal_mid;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 寻找重复的子树
 *
    给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。

    两棵树重复是指它们具有相同的结构以及相同的结点值。

    示例 1：

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
    下面是两个重复的子树：

      2
     /
    4
    和

    4
    因此，你需要以列表的形式返回上述重复子树的根结点。


 */
public class find_duplicate_subtrees_652 {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        if (root==null) return ans;
        saveRoute(root, ans, map);
        return ans;
    }
    private String saveRoute(TreeNode node, List<TreeNode> res, HashMap<String, Integer> map) {
        if (node==null) return "";
        String route = node.val+","+saveRoute(node.left, res, map)+","+saveRoute(node.right, res,map);
        if (map.get(route) != null && map.get(route)==1) {
            res.add(node);
        }
        map.put(route, map.getOrDefault(route, 0)+1);
        return route;
    }
}
