package complete.bfs.tree;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 二叉树的垂序遍历
 *
    给定二叉树，按垂序遍历返回其结点值。

    对位于 (X, Y) 的每个结点而言，其左右子结点分别位于 (X-1, Y-1) 和 (X+1, Y-1)。

    把一条垂线从 X = -infinity 移动到 X = +infinity ，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ Y 坐标递减）。

    如果两个结点位置相同，则首先报告的结点值较小。

    按 X 坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。



    示例 1：



    输入：[3,9,20,null,null,15,7]
    输出：[[9],[3,15],[20],[7]]
    解释：
    在不丧失其普遍性的情况下，我们可以假设根结点位于 (0, 0)：
    然后，值为 9 的结点出现在 (-1, -1)；
    值为 3 和 15 的两个结点分别出现在 (0, 0) 和 (0, -2)；
    值为 20 的结点出现在 (1, -1)；
    值为 7 的结点出现在 (2, -2)。
    示例 2：



    输入：[1,2,3,4,5,6,7]
    输出：[[4],[2],[1,5,6],[3],[7]]
    解释：
    根据给定的方案，值为 5 和 6 的两个结点出现在同一位置。
    然而，在报告 "[1,5,6]" 中，结点值 5 排在前面，因为 5 小于 6。


    提示：

    树的结点数介于 1 和 1000 之间。
    每个结点值介于 0 和 1000 之间。

 */
public class vertical_order_traversal_of_a_binary_tree_987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        Queue<MyNode> q = new LinkedList<>();
        q.offer(new MyNode(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            PriorityQueue<MyNode> qq = new PriorityQueue<>((a, b)-> (a.node.val-b.node.val));
            for (int i=0; i<size; i++) {
                MyNode cur = q.poll();
                qq.add(cur);
                if (cur.node.left != null) {
                    q.offer(new MyNode(cur.node.left, cur.index-1));
                }
                if (cur.node.right != null) {
                    q.offer(new MyNode(cur.node.right, cur.index+1));
                }
            }
            while (!qq.isEmpty()) {
                MyNode cur = qq.poll();
                map.putIfAbsent(cur.index, new ArrayList<>());
                List<Integer> list = map.get(cur.index);
                list.add(cur.node.val);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> list : map.values()) {
            List<Integer> l = new ArrayList<>(list);
            ans.add(l);
        }
        return ans;
    }
    class MyNode{
        public TreeNode node;
        public int index;
        public MyNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
