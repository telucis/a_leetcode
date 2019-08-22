package complete.tree.nAryTree;

import dataStruct.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * N叉树的层序遍历
 *
    给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。

    例如，给定一个 3叉树 :


    返回其层序遍历:

    [
    [1],
    [3,2,4],
    [5,6]
    ]

    说明:

    树的深度不会超过 1000。
    树的节点总数不会超过 5000。

 */
public class n_ary_tree_level_order_traversal_429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> curList = new ArrayList<>();
        curList.add(root.val);
        res.add(curList);
        List<List<Integer>> tmpList = new ArrayList<>();
        if (root.children != null) {
            for (int i=0; i<root.children.size(); i++) {
                List<List<Integer>> chilList = levelOrder(root.children.get(i));
                for (int j=0; j<tmpList.size(); j++) {
                    if (j < chilList.size()) {
                        tmpList.get(j).addAll(chilList.get(j));
                    }
                }
                if (chilList.size() > tmpList.size()) {
                    tmpList.addAll(chilList.subList(tmpList.size(), chilList.size()));
                }
            }
        }
        res.addAll(tmpList);
        return res;
    }

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root==null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            Queue<Node> queue2 = new LinkedList<>();
            for (int i=0; i<size; i++) {
                Node node = queue.poll();
                tmp.add(node.val);
                if (node.children!=null) {
                    for (Node n : node.children) queue2.offer(n);
                }
            }
            res.add(tmp);
            queue = queue2;
        }
        return res;
    }
}
