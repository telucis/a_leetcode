package complete.hashmap.pattern_replace;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 单词模式
 *
    给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。

    这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。

    示例1:

    输入: pattern = "abba", str = "dog cat cat dog"
    输出: true
    示例 2:

    输入:pattern = "abba", str = "dog cat cat fish"
    输出: false
    示例 3:

    输入: pattern = "aaaa", str = "dog cat cat dog"
    输出: false
    示例 4:

    输入: pattern = "abba", str = "dog dog dog dog"
    输出: false
    说明:
    你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。


 */
public class word_pattern_290 {

    public boolean wordPattern(String pattern, String str) {
        char[] pList = pattern.toCharArray();
        String[] sList = str.split(" ");
        if (pList.length != sList.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int i=0; i<pList.length; i++) {
            char c = pList[i];
            String s = sList[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(s)) {
                    return false;
                }
            } else {
                if (set.contains(s)) {
                    return false;
                }
                map.put(c, s);
                set.add(s);
            }
        }
        return true;
    }
}
