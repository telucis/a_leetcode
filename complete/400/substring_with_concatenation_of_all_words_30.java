/**
 * 30. 串联所有单词的子串
 *
    给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

    注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

     

    示例 1：

    输入：
      s = "barfoothefoobarman",
      words = ["foo","bar"]
    输出：[0,9]
    解释：
    从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
    输出的顺序不重要, [9,0] 也是有效答案。
    示例 2：

    输入：
      s = "wordgoodgoodgoodbestword",
      words = ["word","good","best","word"]
    输出：[]
 */

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s.length()==0 || words.length==0) return ans;
        int n = words.length, l=words[0].length();
        int len = n*l;
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) map.put(w, map.getOrDefault(w, 0)+1);
        for (int i=0; i<s.length()-len+1; i++) {
            if (valid(s.substring(i, i+len), map, l, len)) ans.add(i);
        }
        return ans;
    }
    private boolean valid(String w, Map<String, Integer> map, int n, int len) {
        Map<String, Integer> tmp = new HashMap<>();
        for (int i=0; i<len; i+=n) {
            String s = w.substring(i, i+n);
            if (!map.containsKey(s)) return false;
            tmp.put(s, tmp.getOrDefault(s, 0)+1);
            if (map.get(s)<tmp.get(s)) return false;
        }
        return true;
    }
}

