package complete.dp.section;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 两个字符串的删除操作
 *
    给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

    示例 1:

    输入: "sea", "eat"
    输出: 2
    解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
    说明:

    给定单词的长度不超过500。
    给定单词中的字符只含有小写字母。

 */
public class delete_operation_for_two_strings_583 {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i==0 || j==0) {
                    dp[i][j] = 0;
                } else {
                    if (word1.charAt(i-1) == word2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1]+1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return m+n-2*dp[m][n];
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i==0) dp[i][j]=j;
                else if (j==0) dp[i][j]=i;
                else {
                    if (word1.charAt(i-1)==word2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+1;
                    }
                }
                // System.out.print(dp[i][j]+" ");
            }
            // System.out.println("");
        }
        return dp[m][n];
    }
}
