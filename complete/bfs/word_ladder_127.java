package complete.bfs;

import java.util.HashSet;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 单词接龙
 *
    给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

    每次转换只能改变一个字母。
    转换过程中的中间单词必须是字典中的单词。
    说明:

    如果不存在这样的转换序列，返回 0。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    示例 1:

    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    输出: 5

    解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    返回它的长度 5。
    示例 2:

    输入:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    输出: 0

    解释: endWord "cog" 不在字典中，所以无法进行转换。

 */
public class word_ladder_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList==null || wordList.size()==0) {
            return 0;
        }
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> dic = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        int step = 1;
        if (!dic.contains(endWord)) {
            return 0;
        }
        while (!start.isEmpty()) {
            step++;
            HashSet<String> tmpSet = new HashSet<>();
            dic.removeAll(start);
            for (String s : start) {
                char[] arr = s.toCharArray();
                for (int i=0; i<arr.length; i++) {
                    char tmp = arr[i];
                    for (char c='a'; c<='z'; c++) {
                        if (tmp == c) {
                            continue;
                        }
                        arr[i] = c;
                        String strTmp = new String(arr);
                        if (dic.contains(strTmp)) {
                            if (end.contains(strTmp)) {
                                return step;
                            } else {
                                tmpSet.add(strTmp);
                            }
                        }
                    }
                    arr[i] = tmp;
                }
            }
            if (tmpSet.size() < end.size()) {
                start = tmpSet;
            } else {
                start = end;
                end = tmpSet;
            }
        }
        return 0;
    }
}
