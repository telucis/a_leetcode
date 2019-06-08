package medium.greedy;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 最小差值 II
 *
    给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。

    在此过程之后，我们得到一些数组 B。

    返回 B 的最大值和 B 的最小值之间可能存在的最小差值。



    示例 1：

    输入：A = [1], K = 0
    输出：0
    解释：B = [1]
    示例 2：

    输入：A = [0,10], K = 2
    输出：6
    解释：B = [2,8]
    示例 3：

    输入：A = [1,3,6], K = 3
    输出：3
    解释：B = [4,6,3]


    提示：

    1 <= A.length <= 10000
    0 <= A[i] <= 10000
    0 <= K <= 10000

 */
public class smallest_range_ii_910 {

    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int n = A.length, max=A[n-1], min=A[0], res=max-min;
        for (int i=0; i<n-1; i++) {
            max = Math.max(max, A[i]+2*K);
            min = Math.min(A[i+1], A[0]+2*K);
            res = Math.min(res, max-min);
        }
        return res;
    }
}
