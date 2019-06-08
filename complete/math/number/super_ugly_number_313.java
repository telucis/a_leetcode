package complete.math.number;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 超级丑数
 *
    编写一段程序来查找第 n 个超级丑数。

    超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

    示例:

    输入: n = 12, primes = [2,7,13,19]
    输出: 32
    解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
    说明:

    1 是任何给定 primes 的超级丑数。
    给定 primes 中的数字以升序排列。
    0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
    第 n 个超级丑数确保在 32 位有符整数范围内。

 */
public class super_ugly_number_313 {

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n==1) return 1;
        int[] ans = new int[n];
        int[] num = new int[primes.length];
        ans[0] = 1;
        for (int i=1; i<n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j=0; j<primes.length; j++) {
                int tmp = ans[num[j]] * primes[j];
                min = Math.min(min, tmp);
            }
            ans[i] = min;
            for (int j=0; j<primes.length; j++) {
                if (min == ans[num[j]]*primes[j]) {
                    num[j]++;
                }
            }
        }
        return ans[n-1];
    }
}
