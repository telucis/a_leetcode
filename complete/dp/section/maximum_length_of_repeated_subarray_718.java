package complete.dp.section;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 最长重复子数组
 *
    给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

    示例 1:

    输入:
    A: [1,2,3,2,1]
    B: [3,2,1,4,7]
    输出: 3
    解释:
    长度最长的公共子数组是 [3, 2, 1]。
    说明:

    1 <= len(A), len(B) <= 1000
    0 <= A[i], B[i] < 100

 */
public class maximum_length_of_repeated_subarray_718 {

    /**
     * O(n^2) dp
     */
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[2][n+1];
        int max = 0;
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (A[i-1]==B[j-1]) {
                    dp[i%2][j] = dp[(i-1)%2][j-1]+1;
                    max = Math.max(max, dp[i%2][j]);
                } else {
                    dp[i%2][j] = 0;
                }
            }
        }
        return max;
    }

    /**
     * O(n^3)
     */
    public int findLength2(int[] A, int[] B) {
        int count = 0;
        for (int i=0; i<A.length; i++) {
            for (int j=0; j<B.length; j++) {
                int num = 0, ai=i, bi=j;
                while (ai<A.length && bi<B.length && A[ai]==B[bi]) {
                    num++;
                    ai++;
                    bi++;
                }
                count = Math.max(count, num);
            }
        }
        return count;
    }
}
