package complete.bfs;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 单词接龙 II
 *
    给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

    每次转换只能改变一个字母。
    转换过程中的中间单词必须是字典中的单词。
    说明:

    如果不存在这样的转换序列，返回一个空列表。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    示例 1:

    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    输出:
    [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
    ]
    示例 2:

    输入:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    输出: []

    解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。

 */
public class word_ladders_ii_126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> dic = new HashSet<String>(wordList);
        HashMap<String, List<String>> neighbors = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();
        ArrayList<String> solution = new ArrayList<>();

        dic.add(beginWord);
        if (!dic.contains(endWord)) return ans;
        bfs(beginWord, endWord, dic, neighbors, distance);
        dfs(beginWord, endWord, dic, neighbors, distance, solution, ans);
        return ans;
    }
    private void bfs(String start, String end, Set<String> dict, HashMap<String, List<String>> neighbors, HashMap<String, Integer> distance) {
        for (String str : dict) {
            neighbors.put(str, new ArrayList<>());
        }
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distance.put(start, 0);
        while (!q.isEmpty()) {
            int count = q.size();
            boolean foundEnd = false;
            for (int i=0; i<count; i++) {
                String cur = q.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> nb = getNeighbors(cur, dict);
                neighbors.get(cur).addAll(nb);
                for (String n : nb) {
                    if (distance.containsKey(n)) continue;
                    distance.put(n, curDistance+1);
                    if (end.equals(n)) foundEnd=true;
                    else q.offer(n);
                }
                if (foundEnd) break;
            }
        }
    }
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();
        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, List<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) res.add(new ArrayList<>(solution));
        else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur)+1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size()-1);
    }
}
