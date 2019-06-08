package complete.tree.traversal_mid;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 从叶结点开始的最小字符串
 *
    给定一颗根结点为 root 的二叉树，书中的每个结点都有一个从 0 到 25 的值，分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 'b'，依此类推。

    找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。

    （小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。）



    示例 1：



    输入：[0,1,2,3,4,3,4]
    输出："dba"
    示例 2：



    输入：[25,1,3,1,3,0,2]
    输出："adz"
    示例 3：



    输入：[2,2,1,null,1,0,null,0]
    输出："abc"


    提示：

    给定树的结点数介于 1 和 8500 之间。
    树中的每个结点都有一个介于 0 和 25 之间的值。

 */
public class smallest_string_starting_from_leaf_988 {

    public String smallestFromLeaf(TreeNode root) {
        return dfs(root, "");
    }
    private String dfs(TreeNode node, String suffix) {
        if (null == node) {
            return suffix;
        }
        suffix = "" + (char)('a'+node.val) + suffix;
        if (null == node.left && null == node.right) {
            return suffix;
        }
        if (null == node.left || null == node.right) {
            return (null==node.left) ? dfs(node.right, suffix) : dfs(node.left, suffix);
        }
        String left = dfs(node.left, suffix);
        String right = dfs(node.right, suffix);
        return left.compareTo(right) <= 0 ? left : right;
    }
}
