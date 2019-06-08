package complete.dp.coord.array;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 解码方法
 *
    一条包含字母 A-Z 的消息通过以下方式进行了编码：

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    给定一个只包含数字的非空字符串，请计算解码方法的总数。

    示例 1:

    输入: "12"
    输出: 2
    解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
    示例 2:

    输入: "226"
    输出: 3
    解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

 */
public class decode_ways_91 {

    /**
     * dp[s.len+1]
     * for(s.len) dp[i]=dp[i-1], dp[i]+=dp[i-2]
     */
    public int numDecodings(String s) {
        int n = s.length();
        if (n==0) {
            return 0;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)=='0' ? 0 : 1;
        for (int i=2; i<=n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i-1];
            }
            int twoDigits = Integer.parseInt(s.substring(i-2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }
}
