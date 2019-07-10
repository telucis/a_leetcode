package complete.dp.sequence.lis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 最长的斐波那契子序列的长度
 *
    如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

    n >= 3
    对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
    给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

    （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）



    示例 1：

    输入: [1,2,3,4,5,6,7,8]
    输出: 5
    解释:
    最长的斐波那契式子序列为：[1,2,3,5,8] 。
    示例 2：

    输入: [1,3,7,11,12,14,18]
    输出: 3
    解释:
    最长的斐波那契式子序列有：
    [1,11,12]，[3,11,14] 以及 [7,11,18] 。


    提示：

    3 <= A.length <= 1000
    1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
    （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）

 */
public class length_of_longest_fibonacci_subsequence_873 {

    /**
     Save array A to a hash set s.
     Start from base (A[i], A[j]) as the first two element in the sequence,
     we try to find the Fibonacci like subsequence as long as possible,

     Initial (a, b) = (A[i], A[j])
     While the set s contains a + b, we update (a, b) = (b, a + b).
     In the end we update the longest length we find.

     Time Complexity:
     O(N^2logM), where M is the max(A).
     */
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> s = new HashSet<>();
        for (int x : A) {
            s.add(x);
        }
        int res = 2;
        for (int i=0; i<A.length; i++) {
            for (int j=i+1; j<A.length; j++) {
                int a = A[i], b = A[j], l = 2;
                while (s.contains(a+b)) {
                    b = a+b;
                    a = b-a;
                    l++;
                }
                res = Math.max(res, l);
            }
        }
        return res > 2 ? res : 0;
    }

    /**
     Another solution is kind of dp.
     dp[a, b] represents the length of fibo sequence ends up with (a, b)
     Then we have dp[a, b] = (dp[b - a, a] + 1 ) or 2
     The complexity reduce to O(N^2).
     In C++/Java, I use 2D dp and index as key.
     In Python, I use value as key.

     Time Complexity:
     O(N^2)
     */
    public int lenLongestFibSubseq2(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        Map<Integer, Integer> index = new HashMap<>();
        for (int j=0; j<A.length; j++) {
            index.put(A[j], j);
            for (int i=0; i<j; i++) {
                int k = index.getOrDefault(A[j]-A[i], -1);
                dp[i][j] = (A[j]-A[i]<A[i] && k>=0) ? dp[k][i]+1 : 2;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res > 2 ? res : 0;
    }
}
