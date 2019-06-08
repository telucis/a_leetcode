package medium.stack;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 验证二叉树的前序序列化
 *
    序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。

          _9_
         /   \
        3     2
       / \   / \
      4   1  #  6
     / \ / \   / \
    # # # #   # #
    例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。

    给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。

    每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。

    你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。

    示例 1:

    输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
    输出: true
    示例 2:

    输入: "1,#"
    输出: false
    示例 3:

    输入: "9,#,#,1"
    输出: false

 */
public class verify_preorder_serialization_of_a_binary_tree_331 {

    /**
     At first glance, a leaf node's pattern should look like number,#,#,
     start from the beginning of array, once you see this pattern, convert it to a single "#",
     meaning the node with value number has already been fully explored(left subtree, right subtree),
     so we replace it with a "#". While iterating the array, we just keep doing this kind of absorbing/merging backward until we reach the end of array.
     Then we check if the root has been fully explored, which should eventually be a single #.
     During absorbing, if this pattern appears #,#,#, return false.

     "9,3,4,#,#,12,#,#,2,#,6,#,#"

     stack status

     char   stack
     '9':   '9'
     '3':   '3','9'
     '4':   '4','3','9'
     '#':   '#','4','3','9'
     '#':   '#','3','9'
     '12':  'n', '#', '3','9'
     '#':   '#','1', '#', '3','9'
     '#':   '#','3','9' -> '#','9'
     '2':   '2', '#','9'
     '#':   '#', '2', '#','9'
     '6':   '6', '#', '2','#','9'
     '#':   '#', '6', '#', '2','#','9'
     '#':   '#', '2','#','9' -> '#','9' -> '#'
     */
    public boolean isValidSerialization(String preorder) {
        String[] parts = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (int pos=0; pos<parts.length; pos++) {
            String cur = parts[pos];
            while (cur.equals("#") && !stack.isEmpty() && stack.peek().equals(cur)) {
                stack.pop();
                if (stack.isEmpty() || stack.peek().equals("#")) return false;
                stack.pop();
            }
            stack.push(cur);
        }
        return stack.size()==1 && stack.peek().equals("#");
    }
}
