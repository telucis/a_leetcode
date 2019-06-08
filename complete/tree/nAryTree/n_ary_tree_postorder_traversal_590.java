package complete.tree.nAryTree;

import dataStruct.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * N叉树的后序遍历
 *
    给定一个 N 叉树，返回其节点值的后序遍历。

    例如，给定一个 3叉树 :


    返回其后序遍历: [5,6,3,2,4,1].



    说明: 递归法很简单，你可以使用迭代法完成此题吗?


 */
public class n_ary_tree_postorder_traversal_590 {

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (root.children != null) {
            for (int i=0; i<root.children.size(); i++) {
                res.addAll(postorder(root.children.get(i)));
            }
        }
        res.add(root.val);
        return res;
    }
}
