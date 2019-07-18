package complete.dp.backpack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 零钱兑换
 *
    给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

    示例 1:

    输入: coins = [1, 2, 5], amount = 11
    输出: 3
    解释: 11 = 5 + 5 + 1
    示例 2:

    输入: coins = [2], amount = 3
    输出: -1
    说明:
    你可以认为每种硬币的数量是无限的。


 */
public class coin_change_322 {
    /**
     * full backpack
     * dp[coins.len+1][amount+1]
     * dp[i][j]=Math.min(dp[i][j], dp[i-1][j-coins[i-1]*k]+k)
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for (int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }
        for (int j=1; j<=amount; j++) {
            dp[0][j] = -1;
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i-1][j] != -1) dp[i][j] = dp[i-1][j];
                int num = j/coins[i-1];
                for (int k=1; k<=num; k++) {
                    if (dp[i-1][j-coins[i-1]*k] != -1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-coins[i-1]*k]+k);
                    }
                }
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j]=-1;
            }
        }
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=amount; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println("\n");
        }
        return dp[n][amount];
    }

    /**
     * dfs
     */
    Map<Integer, Integer> map = new HashMap<>();
    public int coinChange2(int[] coins, int amount) {
        if (amount==0) {
            return 0;
        }
        if (map.containsKey(amount)) return map.get(amount);
        int n = amount+1;
        for (int coin : coins) {
            int curr=0;
            if (amount>=coin) {
                int next = coinChange2(coins, amount-coin);
                if (next>=0) curr = next+1;
            }
            if (curr>0) n = Math.min(n, curr);
        }
        int finalCount = (n==amount+1) ? -1 : n;
        map.put(amount, finalCount);
        return finalCount;
    }
}
