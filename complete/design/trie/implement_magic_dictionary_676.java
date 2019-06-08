package complete.design.trie;

import java.util.HashSet;

/**
 * Created by telucis on 2019/5/12.
 *
 * 实现一个魔法字典
 *
     实现一个带有buildDict, 以及 search方法的魔法字典。

     对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。

     对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。

     示例 1:

     Input: buildDict(["hello", "leetcode"]), Output: Null
     Input: search("hello"), Output: False
     Input: search("hhllo"), Output: True
     Input: search("hell"), Output: False
     Input: search("leetcoded"), Output: False
     注意:

     你可以假设所有输入都是小写字母 a-z。
     为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
     请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。 请参阅这里了解更多详情。

 */
public class implement_magic_dictionary_676 {
    /**
     * trie
     */
    class MagicDictionary {
        private TrieNode root;
        /** Initialize your data structure here. */
        public MagicDictionary() {
            root = new TrieNode(' ');
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String s : dict) {
                TrieNode node = root;
                for (char c : s.toCharArray())  {
                    if (node.children[c-'a']==null) {
                        node.children[c-'a'] = new TrieNode(c);
                    }
                    node = node.children[c-'a'];
                }
                node.isword = true;
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i=0; i<word.length(); i++) {
                for (char c='a'; c<='z'; c++) {
                    if (chars[i]==c) continue;
                    char org = chars[i];
                    chars[i]=c;
                    if (helper(new String(chars), root)) {
                        return true;
                    }
                    chars[i]=org;
                }
            }
            return false;
        }
        private boolean helper(String s, TrieNode root) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c-'a'] == null) {
                    return false;
                }
                node=node.children[c-'a'];
            }
            return node.isword;
        }
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isword=false;
            char val;
            public TrieNode(char val) {
                this.val = val;
            }
        }
    }

    /**
     * hashmap
     */
    class MagicDictionary2 {
        HashSet<String> set;
        /** Initialize your data structure here. */
        public MagicDictionary2() {
            set = new HashSet<>();
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String str : dict) {
                set.add(str);
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            char[] strs = word.toCharArray();
            for (int i=0; i<word.length(); i++) {
                for (char c='a'; c<='z'; c++) {
                    char org=strs[i];
                    if (strs[i]==c) continue;
                    strs[i]=c;
                    String tmp = new String(strs);
                    if (set.contains(tmp)) {
                        return true;
                    }
                    strs[i]=org;
                }
            }
            return false;
        }
    }
}
