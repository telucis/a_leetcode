package complete.hashmap.easy;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 最长回文串
 *
    给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

    在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

    注意:
    假设字符串的长度不会超过 1010。

    示例 1:

    输入:
    "abccccdd"

    输出:
    7

    解释:
    我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。

 */
public class longest_palindrome_409 {

    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        int res = 0;
        boolean hasMid = false;
        for (Integer num : map.values()) {
            if (num%2 == 0) {
                res += num;
            } else {
                hasMid = true;
                res += num-1;
            }
        }
        if (hasMid) {
            res++;
        }
        return res;
    }
}
