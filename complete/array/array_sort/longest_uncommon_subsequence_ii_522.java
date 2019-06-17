package complete.array.array_sort;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 最长特殊序列 II
 *
    给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。

    子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。

    输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。



    示例：

    输入: "aba", "cdc", "eae"
    输出: 3


    提示：

    所有给定的字符串长度不会超过 10 。
    给定字符串列表的长度将在 [2, 50 ] 之间。



 */
public class longest_uncommon_subsequence_ii_522 {

    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        Set<String> duplicates = getDuplicates(strs);
        for (int i=0; i<strs.length; i++) {
            if (!duplicates.contains(strs[i])) {
                if (i==0) {
                    return strs[0].length();
                }
                for (int j=0; j<i; j++) {
                    if (isSubsequence(strs[j], strs[i])) {
                        break;
                    }
                    if (j==i-1) {
                        return strs[i].length();
                    }
                }
            }
        }
        return -1;
    }
    private boolean isSubsequence(String a, String b) {
        int i=0, j=0;
        while (i<a.length() && j<b.length()) {
            if (a.charAt(i)==b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j==b.length();
    }
    private Set<String> getDuplicates(String[] strs) {
        Set<String> set = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String s : strs) {
            if (set.contains(s)) {
                duplicates.add(s);
            }
            set.add(s);
        }
        return duplicates;
    }
}
