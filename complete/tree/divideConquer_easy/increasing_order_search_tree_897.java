package complete.tree.divideConquer_easy;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 递增顺序查找树
 *
    给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。

    示例 ：

    输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]

          5
         / \
        3    6
       / \    \
      2   4    8
     /        / \
    1        7   9

    输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6
               \
                7
                 \
                  8
                   \
                    9


    提示：

    给定树中的结点数介于 1 和 100 之间。
    每个结点都有一个从 0 到 1000 范围内的唯一整数值。

 */
public class increasing_order_search_tree_897 {

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = new TreeNode(root.val);
        TreeNode left = increasingBST(root.left);
        TreeNode right = increasingBST(root.right);
        node.right = right;
        if (left == null) {
            return node;
        }
        TreeNode newRoot = left;
        while (left.right != null) {
            left = left.right;
        }
        left.right = node;
        return newRoot;
    }
}
