package complete.hashmap.easy;

import dataStruct.ListNode;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 两句话中的不常见单词
 *
    给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）

    如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。

    返回所有不常用单词的列表。

    您可以按任何顺序返回列表。



    示例 1：

    输入：A = "this apple is sweet", B = "this apple is sour"
    输出：["sweet","sour"]
    示例 2：

    输入：A = "apple apple", B = "banana"
    输出：["banana"]


    提示：

    0 <= A.length <= 200
    0 <= B.length <= 200
    A 和 B 都只包含空格和小写字母。

 */
public class uncommon_words_from_two_sentences_884 {

    public String[] uncommonFromSentences(String A, String B) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] wordsA = A.split(" ");
        String[] wordsB = B.split(" ");
        for (int i=0; i<wordsA.length; i++) {
            String w = wordsA[i];
            if (map.containsKey(w)) {
                map.put(w, map.get(w)+1);
            } else {
                map.put(w, 1);
            }
        }
        for (int i=0; i<wordsB.length; i++) {
            String w = wordsB[i];
            if (map.containsKey(w)) {
                map.put(w, map.get(w)+1);
            } else {
                map.put(w, 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (String item : map.keySet()) {
            if (map.get(item) == 1) {
                res.add(item);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
