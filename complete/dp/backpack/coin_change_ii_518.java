package complete.dp.backpack;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 零钱兑换 II
 *
    给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。



    示例 1:

    输入: amount = 5, coins = [1, 2, 5]
    输出: 4
    解释: 有四种方式可以凑成总金额:
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1
    示例 2:

    输入: amount = 3, coins = [2]
    输出: 0
    解释: 只用面额2的硬币不能凑成总金额3。
    示例 3:

    输入: amount = 10, coins = [10]
    输出: 1


    注意:

    你可以假设：

    0 <= amount (总金额) <= 5000
    1 <= coin (硬币面额) <= 5000
    硬币种类不超过 500 种
    结果符合 32 位符号整数

 */
public class coin_change_ii_518 {

    /**
     * full backpack
     * dp[coins.len+1][amount+1]
     * for(k) dp[i][j]+=dp[i-1][j-coins[i-1]*k]
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for (int i=0; i<=n; i++) {
            dp[i][0] = 1;
        }
        for (int j=1; j<=amount; j++) {
            dp[0][j] = 0;
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=amount; j++) {
                dp[i][j] = dp[i-1][j];
                int num = j/coins[i-1];
                for (int k=1; k<=num; k++) {
                    dp[i][j] += dp[i-1][j-coins[i-1]*k];
                }
            }
        }
        return dp[n][amount];
    }
}
