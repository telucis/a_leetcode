package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 序列化和反序列化二叉搜索树
 *
    序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

    设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

    编码的字符串应尽可能紧凑。

    注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。


 */
public class serialize_and_deserialize_bst_449 {

    public class Codec {
        private static final String SEP = ",";
        private static final String NULL = "null";
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) {
                return NULL;
            }
            Stack<TreeNode> st = new Stack<>();
            st.push(root);
            while (!st.empty()) {
                root = st.pop();
                sb.append(root.val).append(SEP);
                if (root.right != null) {
                    st.push(root.right);
                }
                if (root.left != null ) {
                    st.push(root.left);
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals(NULL)) {
                return null;
            }
            String[] strs = data.split(SEP);
            Queue<Integer> q = new LinkedList<>();
            for (String e : strs) {
                q.offer(Integer.parseInt(e));
            }
            return getNode(q);
        }
        private TreeNode getNode(Queue<Integer> q) {
            if (q.isEmpty()) {
                return null;
            }
            TreeNode root = new TreeNode(q.poll());
            Queue<Integer> smallerQueue = new LinkedList<>();
            while (!q.isEmpty() && q.peek()<root.val) {
                smallerQueue.offer(q.poll());
            }
            root.left = getNode(smallerQueue);
            root.right = getNode(q);
            return root;
        }
    }
}
