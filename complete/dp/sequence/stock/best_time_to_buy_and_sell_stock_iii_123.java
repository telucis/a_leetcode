/**
 * 123. 买卖股票的最佳时机 III
 *
     给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

    注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:

    输入: [3,3,5,0,0,3,1,4]
    输出: 6
    解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
         随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
    示例 2:

    输入: [1,2,3,4,5]
    输出: 4
    解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
    示例 3:

    输入: [7,6,4,3,1] 
    输出: 0 
    解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[][] dp = new int[prices.length][K + 1];
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i < prices.length; i++) { 
                int min = Integer.MAX_VALUE;
                //找出第 0 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                for (int buy = 0; buy <= i; buy++) { 
                    min = Math.min(prices[buy] - dp[buy][k - 1], min);
                }
                //比较不操作和选择一天买入的哪个值更大
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[prices.length - 1][K];
    }

    public int maxProfit2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    public int maxProfit3(int[] prices) {
        int n = prices.length, k=2;
        int[][][] dp = new int[n+1][k+1][2];
        for (int i=0; i<k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = Integer.MIN_VALUE;
        }
        for (int i=1; i<=n; i++) {
            for (int j=k; j>0; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]+prices[i-1]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i-1]);
            }
        }
        return dp[n][k][0];
    }
}

