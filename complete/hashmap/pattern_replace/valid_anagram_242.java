package complete.hashmap.pattern_replace;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 有效的字母异位词
 *
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    示例 1:

    输入: s = "anagram", t = "nagaram"
    输出: true
    示例 2:

    输入: s = "rat", t = "car"
    输出: false
    说明:
    你可以假设字符串只包含小写字母。

    进阶:
    如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

 */
public class valid_anagram_242 {

    public boolean isAnagram(String s, String t) {
        int[] x = new int[26];
        int[] y = new int[26];
        for (int i=0; i<s.length(); i++) {
            x[s.charAt(i) - 'a']++;
        }
        for (int i=0; i<t.length(); i++) {
            y[t.charAt(i) - 'a']++;
        }
        for (int i=0; i<26; i++) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }
}
