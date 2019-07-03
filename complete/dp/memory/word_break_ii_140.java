package complete.dp.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 单词拆分 II
 *
    给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

    说明：

    分隔时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。
    示例 1：

    输入:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    输出:
    [
    "cats and dog",
    "cat sand dog"
    ]
    示例 2：

    输入:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    输出:
    [
    "pine apple pen apple",
    "pineapple pen apple",
    "pine applepen apple"
    ]
    解释: 注意你可以重复使用字典中的单词。
    示例 3：

    输入:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出:
    []

 */
public class word_break_ii_140 {

    /**
     * dfs
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return helper(s, wordDict, new HashMap<>());
    }
    private List<String> helper(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        LinkedList<String> res = new LinkedList<>();
        if (s.length()==0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = helper(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
    }


    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<List<String>> dp = new ArrayList<>();
        for (int i=0; i<=s.length(); i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add("");
        for (int i=1; i<=s.length(); i++) {
            for (String str : wordDict) {
                if (str.length()>=i && dp.get(i-str.length()).size()>0) {
                    if (s.substring(i-str.length(), i).equals(str)) {
                        for (String tmp : dp.get(i-str.length())) {
                            if (tmp.isEmpty()) {
                                dp.get(i).add(str);
                            } else {
                                dp.get(i).add(tmp + " " + str);
                            }
                        }
                    }
                }
            }
        }
        return dp.get(s.length());
    }
}
