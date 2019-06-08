package complete.tree.constructTree;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 最大二叉树
 *
    给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

    二叉树的根是数组中的最大元素。
    左子树是通过数组中最大值左边部分构造出的最大二叉树。
    右子树是通过数组中最大值右边部分构造出的最大二叉树。
    通过给定的数组构建最大二叉树，并且输出这个树的根节点。

    Example 1:

    输入: [3,2,1,6,0,5]
    输入: 返回下面这棵树的根节点：

       6
     /   \
    3     5
     \    /
      2  0
       \
        1
    注意:

    给定的数组的大小在 [1, 1000] 之间。

 */
public class maximum_binary_tree_654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length==0) {
            return null;
        }
        if (nums.length==1) {
            return new TreeNode(nums[0]);
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i=0; i<nums.length; i++) {
            if (max<nums[i]) {
                max = nums[i];
                index = i;
            }
        }
        int n = nums.length;
        TreeNode root = new TreeNode(max);
        int[] left = new int[index];
        int[] right = new int[n-index-1];
        for (int i=0; i<index; i++) {
            left[i] = nums[i];
        }
        for (int i=index+1; i<n; i++) {
            right[i-index-1] = nums[i];
        }
        root.left = constructMaximumBinaryTree(left);
        root.right = constructMaximumBinaryTree(right);
        return root;
    }
}
