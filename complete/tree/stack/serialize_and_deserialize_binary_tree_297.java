package complete.tree.stack;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 二叉树的序列化与反序列化
 *
    序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

    请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

    示例:

    你可以将以下二叉树：

      1
     / \
    2   3
       / \
      4   5

    序列化为 "[1,2,3,null,null,4,5]"
    提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

    说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。

 */
public class serialize_and_deserialize_binary_tree_297 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            String ans = "";
            if (root==null) return ans;
            List<String> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur!=null) list.add(String.valueOf(cur.val));
                else list.add("#");
                if (cur!=null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
            while (list.get(list.size()-1).equals("#")) {
                list.remove(list.size()-1);
            }
            for (int i=0; i<list.size()-1; i++) {
                ans+=list.get(i)+",";
            }
            ans+=list.get(list.size()-1);
            return ans;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] list = data.split(",");
            List<TreeNode> queue = new LinkedList<>();
            int index = 0;
            boolean isLeft = true;
            TreeNode root = new TreeNode(Integer.valueOf(list[0]));
            queue.add(root);
            for (int i=1; i<list.length; i++) {
                if (isLeft) {
                    if (!list[i].equals("#")) {
                        TreeNode node = new TreeNode(Integer.valueOf(list[i]));
                        queue.get(index).left = node;
                        queue.add(node);
                    }
                    isLeft = false;
                } else {
                    if (!list[i].equals("#")) {
                        TreeNode node = new TreeNode(Integer.valueOf(list[i]));
                        queue.get(index).right = node;
                        queue.add(node);
                    }
                    isLeft = true;
                    index++;
                }
            }
            return root;
        }
    }
}
