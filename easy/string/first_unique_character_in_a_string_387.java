package easy.string;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 字符串中的第一个唯一字符
 *
    给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

    案例:

    s = "leetcode"
    返回 0.

    s = "loveleetcode",
    返回 2.


    注意事项：您可以假定该字符串只包含小写字母。
 */
public class first_unique_character_in_a_string_387 {

    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c).equals(1)) {
                return i;
            }
        }
        return -1;
    }
}
