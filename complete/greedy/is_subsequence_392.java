package complete.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 判断子序列
 *
    给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

    你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

    字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

    示例 1:
    s = "abc", t = "ahbgdc"

    返回 true.

    示例 2:
    s = "axc", t = "ahbgdc"

    返回 false.

    后续挑战 :

    如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

    致谢:

    特别感谢 @pbrother 添加此问题并且创建所有测试用例。


 */
public class is_subsequence_392 {
    /**
     * binarySearch
     */
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] idx = new List[256];
        for (int i=0; i<t.length(); i++) {
            if (idx[t.charAt(i)] == null) {
                idx[t.charAt(i)] = new ArrayList<>();
            }
            idx[t.charAt(i)].add(i);
        }
        int prev=0;
        for (int i=0; i<s.length(); i++) {
            if (idx[s.charAt(i)] == null) {
                return false;
            }
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j<0) {
                j=-j-1;
            }
            if (j==idx[s.charAt(i)].size()) {
                return false;
            }
            prev = idx[s.charAt(i)].get(j)+1;
        }
        return true;
    }

    /**
     * twoPointer
     */
    public boolean isSubsequence2(String s, String t) {
        if(s.length()==0) {
            return true;
        }
        int indexS=0, indexT=0;
        while (indexT<t.length()) {
            if (t.charAt(indexT)==s.charAt(indexS)) {
                indexS++;
                if (indexS==s.length()) {
                    return true;
                }
            }
            indexT++;
        }
        return false;
    }


    public boolean binarySearch(String s, String t) {
        List<Integer>[] idx = new List[256];
        for (int i=0; i<t.length(); i++) {
            if (idx[t.charAt(i)]==null) {
                idx[t.charAt(i)] = new ArrayList<>();
            }
            idx[t.charAt(i)].add(i);
        }
        int prev = 0;
        for (int i=0; i<s.length(); i++) {
            if (idx[s.charAt(i)] == null) return false;
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j<0) j=-j-1;
            if (j==idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j)+1;
        }
        return true;
    }
}
