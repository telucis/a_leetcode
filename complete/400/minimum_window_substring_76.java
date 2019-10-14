/**
 * 76. 最小覆盖子串
 *
    给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

    示例：

    输入: S = "ADOBECODEBANC", T = "ABC"
    输出: "BANC"
    说明：

    如果 S 中不存这样的子串，则返回空字符串 ""。
    如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */

class Solution {
    public String minWindow(String s, String t) {
        // 记录最短子串的开始位置和长度
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : t.toCharArray()) needs.put(c, needs.getOrDefault(c, 0)+1);
        
        int match = 0;
        
        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0)+1);
                if (window.get(c1) == needs.get(c1)) match++;
            }
            right++;
            
            while (match == needs.size()) {
                if (right - left < minLen) {
                    // 更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2)-1);
                    if (window.get(c2)<needs.get(c2)) match--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ?
                    "" : s.substring(start, start+minLen);
    }
}

