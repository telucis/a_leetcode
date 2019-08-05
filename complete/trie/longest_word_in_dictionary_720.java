package complete.trie;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 词典中最长的单词
 *
    给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

    若无答案，则返回空字符串。

    示例 1:

    输入:
    words = ["w","wo","wor","worl", "world"]
    输出: "world"
    解释:
    单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
    示例 2:

    输入:
    words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
    输出: "apple"
    解释:
    "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
    注意:

    所有输入的字符串都只包含小写字母。
    words数组长度范围为[1,1000]。
    words[i]的长度范围为[1,30]。

 */
public class longest_word_in_dictionary_720 {
    /**
     * trie + dfs
     */
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        root.word = "-";
        for (String word : words) {
            root.insert(word);
        }
        return dfs(root, "");
    }
    private String dfs(TrieNode node, String accum) {
        if (node==null || node.word.length()==0) {
            return accum;
        }
        String res = "";
        if (!node.word.equals("-")) accum = node.word;
        for (TrieNode child : node.links) {
            String curRes = dfs(child, accum);
            if (curRes.length()>res.length() || (curRes.length()==res.length() && curRes.compareTo(res)<0)) {
                res = curRes;
            }
        }
        return res;
    }
    private class TrieNode {
        String word = "";
        TrieNode[] links = new TrieNode[26];
        void insert(String s) {
            char[] chs = s.toCharArray();
            TrieNode curNode = this;
            for (int i=0; i<chs.length; i++) {
                int index = chs[i]-'a';
                if (curNode.links[index]==null) {
                    curNode.links[index] = new TrieNode();
                }
                curNode = curNode.links[index];
            }
            curNode.word = s;
        }
    }

    /**
     * hashmap
     */
    public String longestWord2(String[] words) {
        HashSet<String> set = new HashSet<>();
        Arrays.sort(words);
        String res = "";
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length()-1))) {
                res = word.length() > res.length() ? word : res;
                set.add(word);
            }
        }
        return res;
    }
}
