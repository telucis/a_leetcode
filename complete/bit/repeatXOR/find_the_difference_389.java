package complete.bit.repeatXOR;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 找不同
 *
    给定两个字符串 s 和 t，它们只包含小写字母。

    字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

    请找出在 t 中被添加的字母。



    示例:

    输入：
    s = "abcd"
    t = "abcde"

    输出：
    e

    解释：
    'e' 是那个被添加的字母。

 */
public class find_the_difference_389 {

    public char findTheDifference(String s, String t) {
        return bit(s, t);
    }

    /**
     * bit
     * ^=
     */
    public char bit(String s, String t) {
        char c = 0;
        for (int i=0; i<s.length(); i++) {
            c ^= s.charAt(i);
        }
        for (int i=0; i<t.length(); i++) {
            c ^= t.charAt(i);
        }
        return c;
    }

    public char hashmap(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c) || map.get(c)==0) {
                return c;
            } else {
                map.put(c, map.get(c)-1);
            }
        }
        return '0';
    }
}
