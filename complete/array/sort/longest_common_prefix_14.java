package complete.array.sort;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 最长公共前缀
 *
    编写一个函数来查找字符串数组中的最长公共前缀。

    如果不存在公共前缀，返回空字符串 ""。

    示例 1:

    输入: ["flower","flow","flight"]
    输出: "fl"
    示例 2:

    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
    说明:

    所有输入只包含小写字母 a-z 。

 */
public class longest_common_prefix_14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null) return null;
        if (strs.length == 0) return "";

        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last  = strs[strs.length - 1].toCharArray();

        int i = 0, len = Math.min(first.length, last.length);
        while (i < len && first[i] == last[i]) i++;
        return strs[0].substring(0, i);
    }


    public String longestCommonPrefix2(String[] strs) {
        String res = "";
        if (strs.length == 0) {
            return res;
        }
        int minLen = Integer.MAX_VALUE;
        for (int i=0; i<strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        for (int i=0; i<minLen; i++) {
            char c = strs[0].charAt(i);
            for (int j=1; j<strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return res;
                }
            }
            res += c;
        }
        return res;
    }
}
