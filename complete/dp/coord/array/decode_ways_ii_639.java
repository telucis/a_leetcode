package complete.dp.coord.array;

/**
 * @author karl.wy
 * @date 2019/05/28
 *
 * 解码方法 2
 *
    一条包含字母 A-Z 的消息通过以下的方式进行了编码：

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。

    给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。

    同时，由于结果值可能会相当的大，所以你应当对109 + 7取模。（翻译者标注：此处取模主要是为了防止溢出）

    示例 1 :

    输入: "*"
    输出: 9
    解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
    示例 2 :

    输入: "1*"
    输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18）
    说明 :

    输入的字符串长度范围是 [1, 105]。
    输入的字符串只会包含字符 '*' 和 数字'0' - '9'。

 */
public class decode_ways_ii_639 {
    /**
     * dp[s.len+1]
     * for(s.len+1) dp[i]=dp[i-1]*('*'?9:1), dp[i]+=dp[i-2]*x
     */
    int mod = 1000000007;
    public int numDecodings(String s) {
        int n = s.length();
        if (n==0) return 0;
        long[] dp =  new long[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)=='0' ? 0 : (s.charAt(0)=='*' ? 9 : 1);
        for (int i=2; i<=n; i++) {
            char c = s.charAt(i-1);
            if (c=='*') {
                dp[i] = (dp[i-1] * 9)%mod;
            } else if (c!='0') {
                dp[i] = (dp[i-1])%mod;
            }
            String pre =s.substring(i-2, i);
            if (pre.charAt(0)!='*' && pre.charAt(1)!='*') {
                Integer num = Integer.valueOf(pre);
                if (num>=10 && num<=26) {
                    dp[i]+=dp[i-2];
                }
            } else if (pre.charAt(0)!='*' && pre.charAt(1)=='*') {
                if (pre.charAt(0)=='1') dp[i]+=(dp[i-2]*9)%mod;
                else if (pre.charAt(0)=='2') dp[i]+=(dp[i-2]*6)%mod;
            } else if (pre.charAt(0)=='*' && pre.charAt(1)!='*') {
                if (pre.charAt(1)>'6') dp[i]+=dp[i-2];
                else dp[i]+=(dp[i-2]*2)%mod;
            } else {
                dp[i]+=(dp[i-2]*15)%mod;
            }
        }
        return (int)(dp[n]%mod);
    }
}
