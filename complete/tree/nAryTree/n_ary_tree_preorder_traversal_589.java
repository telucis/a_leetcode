package complete.tree.nAryTree;

import dataStruct.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * N叉树的前序遍历
 *
    给定一个 N 叉树，返回其节点值的前序遍历。

    例如，给定一个 3叉树 :

    返回其前序遍历: [1,3,5,6,2,4]。



    说明: 递归法很简单，你可以使用迭代法完成此题吗?


 */
public class n_ary_tree_preorder_traversal_589 {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        if (root.children != null) {
            for (int i=0; i<root.children.size(); i++) {
                res.addAll(preorder(root.children.get(i)));
            }
        }
        return res;
    }
}
