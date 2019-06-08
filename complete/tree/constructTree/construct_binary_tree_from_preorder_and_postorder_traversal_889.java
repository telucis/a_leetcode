package complete.tree.constructTree;

import dataStruct.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 根据前序和后序遍历构造二叉树
 *
    返回与给定的前序和后序遍历匹配的任何二叉树。

    pre 和 post 遍历中的值是不同的正整数。



    示例：

    输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
    输出：[1,2,3,4,5,6,7]


    提示：

    1 <= pre.length == post.length <= 30
    pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
    每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。

 */
public class construct_binary_tree_from_preorder_and_postorder_traversal_889 {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(post==null || post.length==0 || pre==null || pre.length==0) {
            return null;
        }
        int len = post.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        for (int i=0; i<len; i++) {
            index.put(post[i], i);
        }
        Stack<TreeNode> stack = new Stack<>();
        int i=0;
        TreeNode root = new TreeNode(pre[i++]);
        stack.push(root);
        while (i<len) {
            TreeNode next = new TreeNode(pre[i]);
            while (index.get(stack.peek().val)<index.get(next.val)) {
                stack.pop();
            }
            TreeNode existing = stack.pop();
            if (existing.left == null) {
                existing.left = next;
                stack.push(existing);
                stack.push(next);
            } else {
                existing.right = next;
                stack.push(next);
            }
            i++;
        }
        return root;
    }

    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        TreeNode node = helper(pre, post, pre.length, 0, 0);
        return node;
    }
    private TreeNode helper(int[] pre, int[] post, int len, int s1, int s2) {
        if (len<=0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[s1]);
        if (len==1) {
            return root;
        }
        int nextStart = pre[s1+1];
        int nextEnd = s2;
        for (int i=s2; i<s2+len; i++) {
            if (post[i]==nextStart) {
                nextEnd = i;
            }
        }
        root.left = helper(pre, post, nextEnd-s2+1, nextStart, s2);
        root.right = helper(pre, post, 2*s2+len-nextEnd+2, nextStart+nextEnd-s2+1, nextEnd+1);
        return root;
    }
}
