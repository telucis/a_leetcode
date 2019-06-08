package complete.dp.backpack;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 一和零
 *
    在计算机界中，我们总是追求用有限的资源获取最大的收益。

    现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

    你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

    注意:

    给定 0 和 1 的数量都不会超过 100。
    给定字符串数组的长度不会超过 600。
    示例 1:

    输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
    输出: 4

    解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
    示例 2:

    输入: Array = {"10", "0", "1"}, m = 1, n = 1
    输出: 2

    解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。

 */
public class ones_and_zeros_474 {
    /**
     * 01 backpack
     * dp[strs.len+1][m+1][n+1]
     * dp[i][j][k]=Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l+1][m+1][n+1];
        for (int i=0; i<l+1; i++) {
            int[] nums = new int[2];
            if (i>0) nums=calculate(strs[i-1]);
            for (int j=0; j<m+1; j++) {
                for (int k=0; k<n+1; k++) {
                    if (i==0) dp[i][j][k]=0;
                    else if (j>=nums[0] && k>=nums[1]) {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1);
                    } else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }
        return dp[l][m][n];
    }
    private int[] calculate(String str) {
        int[] res = new int[2];
        for (char c : str.toCharArray()) {
            if (c == '0') {
                res[0]++;
            } else {
                res[1]++;
            }
        }
        return res;
    }
}
