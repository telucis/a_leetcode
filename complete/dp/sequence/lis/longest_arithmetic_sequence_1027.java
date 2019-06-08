package complete.dp.sequence.lis;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/05/19
 *
 * 最长等差数列
 *
    给定一个整数数组 A，返回 A 中最长等差子序列的长度。

    回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。



    示例 1：

    输入：[3,6,9,12]
    输出：4
    解释：
    整个数组是公差为 3 的等差数列。
    示例 2：

    输入：[9,4,7,2,10]
    输出：3
    解释：
    最长的等差子序列是 [4,7,10]。
    示例 3：

    输入：[20,1,15,3,10,5,8]
    输出：4
    解释：
    最长的等差子序列是 [20,15,10,5]。


    提示：

    2 <= A.length <= 2000
    0 <= A[i] <= 10000

 */
public class longest_arithmetic_sequence_1027 {

    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        if (n<=2) return n;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        int ans = 2;
        for (int i=0; i<A.length; i++) {
            dp[i] = new HashMap<>();
            for (int j=0; j<i; j++) {
                int d = A[i]-A[j];
                int len = 2;
                if (dp[j].containsKey(d)) {
                    len = dp[j].get(d)+1;
                }
                dp[i].put(d, Math.max(len, dp[i].getOrDefault(d, 2)));
                ans = Math.max(ans, dp[i].get(d));
            }
        }
        return ans;
    }
    /**
     * O(n^3)
     */
    public int longestArithSeqLength2(int[] A) {
        int n = A.length;
        int max = 0;
        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){

                int d = A[j] - A[i];
                int next = A[j] + d;
                int cnt = 2;
                for (int k = j + 1; k < n; k++){
                    if(A[k] == next){
                        cnt++;
                        next = A[k] + d;
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        return max;
    }
}
