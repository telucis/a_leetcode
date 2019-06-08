package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 从前序与中序遍历序列构造二叉树
 *
    根据一棵树的前序遍历与中序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    返回如下的二叉树：

      3
     / \
    9  20
      /  \
     15   7

 */
public class construct_binary_tree_from_preorder_and_inorder_traversal_105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, preorder.length, 0, 0);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int length, int preIndex, int inIndex) {
        if (length==0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIndex]);
        if (length==1) {
            return root;
        }
        int index = 0;
        for (int i=0; i<length; i++) {
            if (inorder[inIndex+i] == preorder[preIndex]) {
                index = i;
            }
        }
        root.left = helper(preorder, inorder, index, preIndex+1, inIndex);
        root.right = helper(preorder, inorder, length-index-1, preIndex+index+1, inIndex+index+1);
        return root;
    }
}
