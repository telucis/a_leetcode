package easy.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/04/15
 *
 * 同构字符串

    给定两个字符串 s 和 t，判断它们是否是同构的。

    如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

    所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

    示例 1:

    输入: s = "egg", t = "add"
    输出: true
    示例 2:

    输入: s = "foo", t = "bar"
    输出: false
    示例 3:

    输入: s = "paper", t = "title"
    输出: true
    说明:
    你可以假设 s 和 t 具有相同的长度。

 */
public class isomorphic_strings_205 {

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        for (int i=0; i<len; i++) {
            if (mapS.containsKey(s.charAt(i)) && mapT.containsKey(t.charAt(i))) {
                if (!mapS.get(s.charAt(i)).equals(mapT.get(t.charAt(i)))) {
                    return false;
                }
            } else if (!mapS.containsKey(s.charAt(i)) && !mapT.containsKey(t.charAt(i))) {
                mapS.put(s.charAt(i), i);
                mapT.put(t.charAt(i), i);
            } else {
                return false;
            }
        }
        return true;
    }
}
