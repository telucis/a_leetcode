package complete.design.trie;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 添加与搜索单词 - 数据结构设计
 *
    设计一个支持以下两种操作的数据结构：

    void addWord(word)
    bool search(word)
    search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

    示例:

    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
    说明:

    你可以假设所有单词都是由小写字母 a-z 组成的。


 */
public class add_and_search_word_data_structure_design_211 {

    class WordDictionary {
        class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public String item="";
        }
        private TrieNode root = new TrieNode();
        /** Initialize your data structure here. */
        public WordDictionary() { }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c-'a']==null) {
                    node.children[c-'a'] = new TrieNode();
                }
                node = node.children[c-'a'];
            }
            node.item = word;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }
        private boolean match(char[] chs, int k, TrieNode node) {
            if (k==chs.length) return !node.item.equals("");
            if (chs[k]!='.') {
                return node.children[chs[k]-'a'] !=null && match(chs, k+1, node.children[chs[k]-'a']);
            } else {
                for (int i=0; i<26; i++) {
                    if (node.children[i]!=null) {
                        if (match(chs, k+1, node.children[i])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
