package complete.hashmap.mid;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 匹配子序列的单词数
 *
    给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。

    示例:
    输入:
    S = "abcde"
    words = ["a", "bb", "acd", "ace"]
    输出: 3
    解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
    注意:

    所有在words和 S 里的单词都只由小写字母组成。
    S 的长度在 [1, 50000]。
    words 的长度在 [1, 5000]。
    words[i]的长度在[1, 50]。

 */
public class number_of_matching_subsequences_792 {

    /**
     * O(S+W*L)
     */
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c='a'; c<='z'; c++) {
            map.putIfAbsent(c, new LinkedList<>());
        }
        for (String word : words) {
            map.get(word.charAt(0)).addLast(word);
        }
        int count = 0;
        for (char c : S.toCharArray()) {
            Deque<String> queue = map.get(c);
            int size = queue.size();
            for (int i=0; i<size; i++) {
                String word = queue.removeFirst();
                if (word.length()==1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).addLast(word.substring(1));
                }
            }
        }
        return count;
    }
}
