package complete.dp.palindromic;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 最长回文子序列
 *
    给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。

    示例 1:
    输入:

    "bbbab"
    输出:

    4
    一个可能的最长回文子序列为 "bbbb"。

    示例 2:
    输入:

    "cbbd"
    输出:

    2
    一个可能的最长回文子序列为 "bb"。


 */
public class longest_palindromic_subsequence_516 {

    /**
     dp[i][j]: the longest palindromic subsequence's length of substring(i, j), here i, j represent left, right indexes in the string
     State transition:
     dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
     otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
     Initialization: dp[i][i] = 1
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i=s.length()-1; i>=0; i--) {
            dp[i][i]=1;
            for (int j=i+1; j<s.length(); j++) {
                if (s.charAt(i)==s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1]+2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
