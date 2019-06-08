package medium.hashMap;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 字母异位词分组
 *
    给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

    示例:

    输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
    输出:
    [
    ["ate","eat","tea"],
    ["nat","tan"],
    ["bat"]
    ]
    说明：

    所有输入均为小写字母。
    不考虑答案输出的顺序。

 */
public class group_anagrams_49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
