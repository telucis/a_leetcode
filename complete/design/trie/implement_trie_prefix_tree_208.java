package complete.design.trie;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 实现 Trie (前缀树)
 *
    实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

    示例:

    Trie trie = new Trie();

    trie.insert("apple");
    trie.search("apple");   // 返回 true
    trie.search("app");     // 返回 false
    trie.startsWith("app"); // 返回 true
    trie.insert("app");
    trie.search("app");     // 返回 true
    说明:

    你可以假设所有的输入都是由小写字母 a-z 构成的。
    保证所有输入均为非空字符串。

 */
public class implement_trie_prefix_tree_208 {

    class Trie {
        Trie[] children;
        boolean isWord;
        /** Initialize your data structure here. */
        public Trie() {
            children = new Trie[26];
            isWord = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie cur=this;
            for (char c : word.toCharArray()) {
                if (cur.children[c-'a']==null) cur.children[c-'a'] = new Trie();
                cur = cur.children[c-'a'];
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                if (cur.children[c-'a']==null) return false;
                else cur = cur.children[c-'a'];
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie cur = this;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c-'a']==null) return false;
                else cur = cur.children[c-'a'];
            }
            return true;
        }
    }
}
