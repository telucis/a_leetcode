package medium.twoPoint;

import java.util.Collections;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 通过删除字母匹配到字典里最长单词
 *
    给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

    示例 1:

    输入:
    s = "abpcplea", d = ["ale","apple","monkey","plea"]

    输出:
    "apple"
    示例 2:

    输入:
    s = "abpcplea", d = ["a","b","c"]

    输出:
    "a"
    说明:

    所有输入的字符串只包含小写字母。
    字典的大小不会超过 1000。
    所有输入的字符串长度不会超过 1000。

 */
public class longest_word_in_dictionary_through_deleting_524 {

    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (a, b)-> a.length()!=b.length() ? b.length()-a.length() : a.compareTo(b));
        for (String dictWord : d) {
            int i=0;
            for (char c : s.toCharArray()) {
                if (i < dictWord.length() && c==dictWord.charAt(i)) i++;
            }
            if (i==dictWord.length()) return dictWord;
        }
        return "";
    }

    /**
     * without sort
     */
    public String findLongestWord2(String s, List<String> d) {
        String longest = "";
        for (String w : d) {
            int i=0;
            for (char c : s.toCharArray()) {
                if (i<w.length() && c==w.charAt(i)) i++;
            }
            if (i==w.length() && w.length()>=longest.length()) {
                if (w.length()>longest.length() || w.compareTo(longest)<0) {
                    longest = w;
                }
            }
        }
        return longest;
    }
}
