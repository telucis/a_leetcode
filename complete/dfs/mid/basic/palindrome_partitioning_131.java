package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 分割回文串
 *
    给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

    返回 s 所有可能的分割方案。

    示例:

    输入: "aab"
    输出:
    [
    ["aa","b"],
    ["a","a","b"]
    ]

 */
public class palindrome_partitioning_131 {

    /**
     * backtack(ans, tmp, s, i+1)
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        backtack(ans, new LinkedList<>(), s, 0);
        return ans;
    }
    private void backtack(List<List<String>> ans, LinkedList<String> tmp, String s, int start) {
        if (start==s.length()) ans.add(new ArrayList<>(tmp));
        else {
            for (int i=start; i<s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tmp.add(s.substring(start, i+1));
                    backtack(ans, tmp, s, i+1);
                    tmp.removeLast();
                }
            }
        }
    }
    private boolean isPalindrome(String s, int low, int hight) {
        while (low<hight) {
            if (s.charAt(low++)!=s.charAt(hight--)) return false;
        }
        return true;
    }



    public List<List<String>> partition2(String s) {
        List<List<String>> ans = new ArrayList<>();
        helper(s, new LinkedList<>(), ans);
        return ans;
    }
    private void helper(String s, LinkedList<String> tmp, List<List<String>> ans) {
        if (s.length()==0) {
            if (tmp.size()>0) ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=1; i<=s.length(); i++) {
            if (judge(s.substring(0, i))) {
                tmp.add(s.substring(0, i));
                helper(s.substring(i), tmp, ans);
                tmp.removeLast();
            }
        }
    }
    private boolean judge(String s) {
        int n = s.length(), start=0, end=n-1;
        while (start<end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
