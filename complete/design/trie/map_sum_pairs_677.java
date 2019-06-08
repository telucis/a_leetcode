package complete.design.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 键值映射
 *
    实现一个 MapSum 类里的两个方法，insert 和 sum。

    对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

    对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

    示例 1:

    输入: insert("apple", 3), 输出: Null
    输入: sum("ap"), 输出: 3
    输入: insert("app", 2), 输出: Null
    输入: sum("ap"), 输出: 5

 */
public class map_sum_pairs_677 {

    class MapSum {
        class Trie {
            Map<Character, Trie> children;
            boolean isWord;
            int value;

            public Trie() {
                children = new HashMap<>();
                isWord = false;
                value=0;
            }
        }
        Trie root;
        /** Initialize your data structure here. */
        public MapSum() {
            root = new Trie();
        }

        public void insert(String key, int val) {
            Trie curr = root;
            for (char c : key.toCharArray()) {
                curr.children.putIfAbsent(c, new Trie());
                curr = curr.children.get(c);
            }
            curr.isWord = true;
            curr.value = val;
        }

        public int sum(String prefix) {
            Trie cur = root;
            for (char c : prefix.toCharArray()) {
                Trie next = cur.children.get(c);
                if (next == null) return 0;
                cur = next;
            }
            return dfs(cur);
        }
        private int dfs(Trie node) {
            int sum = 0;
            for (char c : node.children.keySet()) {
                sum += dfs(node.children.get(c));
            }
            return sum+node.value;
        }
    }
}
