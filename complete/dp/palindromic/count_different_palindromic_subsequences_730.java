package complete.dp.palindromic;

/**
 * @author karl.wy
 * @date 2019/05/29
 *
 * 统计不同回文子字符串
 *
    给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。

    通过从 S 中删除 0 个或多个字符来获得子字符序列。

    如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。

    如果对于某个  i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。



    示例 1：

    输入：
    S = 'bccb'
    输出：6
    解释：
    6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
    注意：'bcb' 虽然出现两次但仅计数一次。
    示例 2：

    输入：
    S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
    输出：104860361
    解释：
    共有 3104860382 个不同的非空回文子字符序列，对 10^9 + 7 取模为 104860361。


    提示：

    字符串 S 的长度将在[1, 1000]范围内。
    每个字符 S[i] 将会是集合 {'a', 'b', 'c', 'd'} 中的某一个。

 */
public class count_different_palindromic_subsequences_730 {

    int mod = 1000000007;
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int[][] dp = new int[n][n];
        for (int i=0; i<n; i++) dp[i][i]=1;
        char[] chs = S.toCharArray();

        for (int len=1; len<n; len++) {
            for (int i=0; i<n-len; i++) {
                int j = i+len;
                if (chs[i]==chs[j]) {
                    int low = i+1, hight = j-1;
                    /* Variable low and high here are used to get rid of the duplicate*/
                    while (low<=hight && chs[low]!=chs[j]) low++;
                    while (low<=hight && chs[hight]!=chs[j]) hight--;

                    if (low>hight) {
                        dp[i][j] = dp[i+1][j-1]*2 + 2;
                    } else if (low==hight) {
                        dp[i][j] = dp[i+1][j-1]*2+1;
                    } else {
                        dp[i][j] = dp[i+1][j-1]*2 - dp[low+1][hight-1];
                    }
                } else {
                    dp[i][j] = dp[i][j-1]+dp[i+1][j]-dp[i+1][j-1];
                }
                dp[i][j] = dp[i][j]<0 ? dp[i][j]+mod : dp[i][j]%mod;
            }
        }
        return dp[0][n-1];
    }
}
