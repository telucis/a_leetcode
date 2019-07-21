package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 将有序数组转换为二叉搜索树
 *
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

     0
    / \
  -3   9
  /   /
-10  5

 */
public class convert_sorted_array_to_binary_search_tree_108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return null;
        }
        TreeNode node = new TreeNode(nums[n/2]);
        int[] left, right;
        left = new int[n/2];
        if (n%2 == 1) {
            right = new int[n/2];
        } else {
            right = new int[n/2-1];
        }
        for (int i=0; i<n/2; i++) {
            left[i] = nums[i];
        }
        for (int i=n/2+1; i<nums.length; i++) {
            right[i-n/2-1] = nums[i];
        }
        node.left = sortedArrayToBST(left);
        node.right = sortedArrayToBST(right);
        return node;
    }
}
