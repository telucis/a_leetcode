package complete.tree.trie;

import java.util.List;

/**
 * Created by telucis on 2019/5/12.
 *
 * 单词替换
 *
     在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

     现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

     你需要输出替换之后的句子。

     示例 1:

     输入: dict(词典) = ["cat", "bat", "rat"]
     sentence(句子) = "the cattle was rattled by the battery"
     输出: "the cat was rat by the bat"
     注:

     输入只包含小写字母。
     1 <= 字典单词数 <=1000
     1 <=  句中词语数 <= 1000
     1 <= 词根长度 <= 100
     1 <= 句中词语长度 <= 1000

 */
public class replace_words_648 {

    /**
     * trie
     */
    public String replaceWords(List<String> dict, String sentence) {
        String[] tokens = sentence.split(" ");
        TrieNode trie = buildTrie(dict);
        return replaceWords(tokens, trie);
    }
    private String replaceWords(String[] tokens, TrieNode root) {
        StringBuffer sb = new StringBuffer();
        for (String token : tokens) {
            sb.append(getShortestReplacement(token, root));
            sb.append(" ");
        }
        return sb.substring(0, sb.length()-1);
    }
    private String getShortestReplacement(String token, final TrieNode root) {
        TrieNode tmp = root;
        StringBuilder sb = new StringBuilder();
        for (char c : token.toCharArray()) {
            sb.append(c);
            if (tmp.children[c-'a'] != null) {
                if (tmp.children[c-'a'].isWord) {
                    return sb.toString();
                }
                tmp = tmp.children[c-'a'];
            } else {
                return token;
            }
        }
        return token;
    }
    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode(' ');
        for (String word : dict) {
            TrieNode tmp = root;
            for (char c : word.toCharArray()) {
                if (tmp.children[c-'a']==null) {
                    tmp.children[c-'a'] = new TrieNode(c);
                }
                tmp = tmp.children[c-'a'];
            }
            tmp.isWord = true;
        }
        return root;
    }
    public class TrieNode {
        char val;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            this.children = new TrieNode[26];
            this.isWord=false;
        }
    }


    /**
     *
     */
    public String replaceWords2(List<String> dict, String sentence) {
        String[] strs = sentence.split(" ");
        for (int i=0; i<strs.length; i++) {
            for (String d : dict) {
                if (strs[i].startsWith(d)) {
                    strs[i] = d;
                }
            }
        }
        String ans = "";
        for (String s : strs) {
            ans += s+" ";
        }
        return ans.substring(0, ans.length()-1);
    }
}
