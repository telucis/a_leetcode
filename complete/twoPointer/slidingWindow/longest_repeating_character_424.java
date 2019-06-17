package complete.twoPointer.slidingWindow;

/**
 * @author karl.wy
 * @date 2019/05/11
 *
 * 替换后的最长重复字符
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

    注意:
    字符串长度 和 k 不会超过 104。

    示例 1:

    输入:
    s = "ABAB", k = 2

    输出:
    4

    解释:
    用两个'A'替换为两个'B',反之亦然。
    示例 2:

    输入:
    s = "AABABBA", k = 1

    输出:
    4

    解释:
    将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
    子串 "BBBB" 有最长重复字母, 答案为 4。

 */
public class longest_repeating_character_424 {

    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start=0, maxCount=0, maxLength=0;
        for (int end=0; end<len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end)-'A']);
            if (end-start+1-maxCount > k) {
                count[s.charAt(start)-'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end-start+1);
        }
        return maxLength;
    }
}
