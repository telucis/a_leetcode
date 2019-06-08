package complete.dp.palindromic;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 最长回文子串
 *
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    示例 1：

    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：

    输入: "cbbd"
    输出: "bb"

 */
public class longest_palindromic_substring_5 {
    int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len<2) {
            return s;
        }
        for (int i=0; i<len-1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }
        return s.substring(lo, lo+maxLen);
    }
    private void extendPalindrome(String s, int j, int k) {
        while (j>=0 && k<s.length() && s.charAt(j)==s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k-j-1) {
            maxLen = k-j-1;
            lo = j+1;
        }
    }

    /**
     * dp
     */
    public String longestPalindrome_dp(String s) {
        if (s.length()==0) return "";
        int n=s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i=0; i<n; i++) {
            dp[i][i] = true;
            if (i==n-1) break;
            if (s.charAt(i)==s.charAt(i+1)) dp[i][i+1] = true;
        }
        // dp
        for (int i=n-3; i>=0; i--) {
            for (int j=i+2; j<n; j++) {
                dp[i][j] = dp[i+1][j-1] && s.charAt(i)==s.charAt(j);
            }
        }
        int max=0;
        String maxstr = "";
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if (dp[i][j] && j-i+1>max) {
                    max = j-i+1;
                    maxstr = s.substring(i, j+1);
                }
            }
        }
        return maxstr;
    }
}
