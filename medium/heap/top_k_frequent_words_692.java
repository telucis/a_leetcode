package medium.heap;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 前K个高频单词
 *
    给一非空的单词列表，返回前 k 个出现次数最多的单词。

    返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

    示例 1：

    输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
    输出: ["i", "love"]
    解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。


    示例 2：

    输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
    输出: ["the", "is", "sunny", "day"]
    解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。


    注意：

    假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
    输入的单词均由小写字母组成。


    扩展练习：

    尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。

 */
public class top_k_frequent_words_692 {

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0)+1);
        }
        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
            (a, b) -> {
                if (!a.getValue().equals(b.getValue())) {
                    return a.getValue()-b.getValue();
                } else {
                    return b.getKey().compareTo(a.getKey());
                }
            }
        );
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            q.offer(e);
            if (q.size()>k) {
                q.poll();
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}
