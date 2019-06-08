package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 先序遍历构造二叉树
 *
    返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。

    (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）



    示例：

    输入：[8,5,1,7,10,12]
    输出：[8,5,10,1,7,null,12]



    提示：

    1 <= preorder.length <= 100
    先序 preorder 中的值是不同的。

 */
public class construct_binary_search_tree_from_preorder_traversal_1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length==0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length==1) {
            return root;
        }
        int n = preorder.length;
        int index = -1;
        for (int i=1; i<n; i++) {
            if (preorder[i]>preorder[0]) {
                index = i;
                break;
            }
        }
        int[] left = new int[index!=-1 ? index-1 : n-1];
        int[] right = new int[index!=-1 ? n-index : 0];
        for (int i=1; i<(index==-1?n:index); i++) {
            left[i-1] = preorder[i];
        }
        for (int i=(index==-1?n:index); i<n; i++) {
            right[i-index] = preorder[i];
        }
        root.left = bstFromPreorder(left);
        root.right = bstFromPreorder(right);
        return root;
    }
}
