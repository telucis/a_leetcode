package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 从中序与后序遍历序列构造二叉树
 *
    根据一棵树的中序遍历与后序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    中序遍历 inorder = [9,3,15,20,7]
    后序遍历 postorder = [9,15,7,20,3]
    返回如下的二叉树：

    3
    / \
    9  20
    /  \
    15   7

 */
public class construct_bianry_tree_from_inorder_and_postorder_traversal_106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, postorder.length, postorder.length);
    }
    private TreeNode build(int[] inorder, int[] postorder, int inStart, int postEnd, int length) {
        if (length==0) {
            return null;
        }
        int root = postorder[postEnd-1];
        TreeNode treeNode = new TreeNode(root);
        if (length==1) {
            return treeNode;
        }
        for (int i=length-1; i>=0; i--) {
            if (root == inorder[inStart+i]) {
                treeNode.left = build(inorder, postorder, inStart, postEnd-length+i, i);
                treeNode.right = build(inorder, postorder, inStart+i+1, postEnd-1, length-1-i);
                return treeNode;
            }
        }
        return null;
    }
}
