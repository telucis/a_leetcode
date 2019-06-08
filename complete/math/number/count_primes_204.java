package complete.math.number;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/15
 *
 * 计数质数

    统计所有小于非负整数 n 的质数的数量。

    示例:

    输入: 10
    输出: 4
    解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

 */
public class count_primes_204 {

    public int countPrimes(int n) {
        int[] list = new int[n];
        if (n < 3) {
            return 0;
        }
        Arrays.fill(list, 1);
        list[0] = 0;
        list[1] = 0;
        for (int i=2; i<n; i++) {
            if (list[i] == 1) {
                for (int k=2, j=2*i; j<n; k++) {
                    list[j] = 0;
                    j = k*i;
                }
            }
        }
        int res = 0;
        for (int i=2; i<n; i++) {
            if (list[i] == 1) {
                res++;
            }
        }
        return res;
    }
}
