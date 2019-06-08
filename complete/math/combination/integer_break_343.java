package complete.math.combination;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 整数拆分
 *
    给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

    示例 1:

    输入: 2
    输出: 1
    解释: 2 = 1 + 1, 1 × 1 = 1。
    示例 2:

    输入: 10
    输出: 36
    解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
    说明: 你可以假设 n 不小于 2 且不大于 58。


 */
public class integer_break_343 {

    /**
     * dp
     */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i=2; i<n+1; i++) {
            for (int j=i-1; j>=1; j--) {
                dp[i] = Math.max(dp[i], dp[j]*(i-j));
                dp[i] = Math.max(dp[i], j*(i-j));
            }
        }
        return dp[n];
    }

    /**
     * math
     *
         The first thing we should consider is : What is the max product if we break a number N into two factors?

         I use a function to express this product: f=x(N-x)

         When x=N/2, we get the maximum of this function.

         However, factors should be integers. Thus the maximum is (N/2)*(N/2) when N is even or (N-1)/2 *(N+1)/2 when N is odd.

         When the maximum of f is larger than N, we should do the break.

         (N/2)*(N/2)>=N, then N>=4

         (N-1)/2 *(N+1)/2>=N, then N>=5

         These two expressions mean that factors should be less than 4, otherwise we can do the break and get a better product. The factors in last result should be 1, 2 or 3. Obviously, 1 should be abandoned. Thus, the factors of the perfect product should be 2 or 3.

         The reason why we should use 3 as many as possible is

         For 6, 3 * 3>2 * 2 * 2. Thus, the optimal product should contain no more than three 2.

         Below is my accepted, O(N) solution.
     */
    public int integerBreak2(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        while(n>4){
            product*=3;
            n-=3;
        }
        product*=n;

        return product;
    }
}
