package complete.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 单词搜索 II
 *
    给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。

    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

    示例:

    输入:
    words = ["oath","pea","eat","rain"] and board =
    [
    ['o','a','a','n'],
    ['e','t','a','e'],
    ['i','h','k','r'],
    ['i','f','l','v']
    ]

    输出: ["eat","oath"]
    说明:
    你可以假设所有输入都由小写字母 a-z 组成。

    提示:

    你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
    如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。

 */
public class word_search_ii_212 {

    public List<String> findWords(char[][] board, String[] words) {
        int m=board.length, n=board[0].length;
        Trie root = buildTrie(words);
        List<String> ans = new ArrayList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dfs(board, i, j, root, ans);
            }
        }
        return ans;
    }
    private void dfs(char[][] board, int x, int y, Trie cur, List<String> ans) {
        char c = board[x][y];
        if (c=='#' || cur.children[c-'a']==null) return;
        cur = cur.children[c-'a'];
        if (!cur.item.isEmpty()) {
            ans.add(cur.item);
            cur.item="";
        }
        board[x][y]='#';
        if (x>0) dfs(board, x-1, y, cur, ans);
        if (y>0) dfs(board, x, y-1, cur, ans);
        if (x<board.length-1) dfs(board, x+1, y, cur, ans);
        if (y<board[0].length-1) dfs(board, x, y+1, cur, ans);
        board[x][y]=c;
    }
    private Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie p = root;
            for (char c : w.toCharArray()) {
                int i=c-'a';
                if (p.children[i]==null) p.children[i]=new Trie();
                p=p.children[i];
            }
            p.item=w;
        }
        return root;
    }
    class Trie {
        String item;
        Trie[] children;
        public Trie() {
            item="";
            children = new Trie[26];
        }
    }
}
