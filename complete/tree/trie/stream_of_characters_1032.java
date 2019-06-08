package complete.tree.trie;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 字符流
 *
    按下述要求实现 StreamChecker 类：

    StreamChecker(words)：构造函数，用给定的字词初始化数据结构。
    query(letter)：如果存在某些 k >= 1，可以用查询的最后 k个字符（按从旧到新顺序，包括刚刚查询的字母）拼写出给定字词表中的某一字词时，返回 true。否则，返回 false。


    示例：

    StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // 初始化字典
    streamChecker.query('a');          // 返回 false
    streamChecker.query('b');          // 返回 false
    streamChecker.query('c');          // 返回 false
    streamChecker.query('d');          // 返回 true，因为 'cd' 在字词表中
    streamChecker.query('e');          // 返回 false
    streamChecker.query('f');          // 返回 true，因为 'f' 在字词表中
    streamChecker.query('g');          // 返回 false
    streamChecker.query('h');          // 返回 false
    streamChecker.query('i');          // 返回 false
    streamChecker.query('j');          // 返回 false
    streamChecker.query('k');          // 返回 false
    streamChecker.query('l');          // 返回 true，因为 'kl' 在字词表中。


    提示：

    1 <= words.length <= 2000
    1 <= words[i].length <= 2000
    字词只包含小写英文字母。
    待查项只包含小写英文字母。
    待查项最多 40000 个。

 */
public class stream_of_characters_1032 {

    class StreamChecker {
        Trie root;
        public StreamChecker(String[] words) {
            root = new Trie();
            for (String w : words) {
                Trie cur=root;
                for (int i=w.length()-1; i>=0; i--) {
                    int n = w.charAt(i)-'a';
                    if (cur.childs[n]==null) cur.childs[n]=new Trie();
                    cur = cur.childs[n];
                }
                cur.isWord=true;
            }
        }
        StringBuilder word=new StringBuilder();
        public boolean query(char letter) {
            word.append(letter);
            Trie cur = root;
            for (int i=word.length()-1; i>=0; i--) {
                int n = word.charAt(i)-'a';
                if (cur.childs[n]==null) return false;
                if (cur.childs[n].isWord) return true;
                cur = cur.childs[n];
            }
            return false;
        }
        private class Trie {
            Trie[] childs;
            boolean isWord;
            public Trie() {
                childs=new Trie[26];
            }
        }
    }
}
