package complete.dfs.mid.divideConquer;

import java.util.ArrayList;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 漂亮数组
 *
    对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：

    对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。

    那么数组 A 是漂亮数组。



    给定 N，返回任意漂亮数组 A（保证存在一个）。



    示例 1：

    输入：4
    输出：[2,1,4,3]
    示例 2：

    输入：5
    输出：[3,1,2,5,4]


    提示：

    1 <= N <= 1000

 */
public class beautiful_array_932 {
    /**
         Intuition:
         Try to divide and conquer,
         so we have left part, right part.

         One way is to divide into [1, N / 2] and [N / 2 + 1, N].
         But it will cause problems when we merge them.

         Another way is to divide into odds part and evens part.
         So there is no k with A[k] * 2 = odd + even

         I brute force all permutations when N = 5:
         20 beautiful array found,
         only 4 don't fit odd + even pattern:
         [2, 1, 4, 5, 3]
         [3, 1, 2, 5, 4]
         [3, 5, 4, 1, 2]
         [4, 5, 2, 1, 3]


         Beautiful Array Properties
         Saying that an array is beautiful,
         there is no i < k < j,
         such that A[k] * 2 = A[i] + A[j]

         Apply these 3 following changes a beautiful array,
         we can get a new beautiful array


         1. Deletion
         Easy to prove.

         2. Addition
         If we have A[k] * 2 != A[i] + A[j],
         (A[k] + x) * 2 = A[k] * 2 + 2x != A[i] + A[j] + 2x = (A[i] + x) + (A[j] + x)

         E.g: [1,3,2] + 1 = [2,4,3].

         3. Multiplication
         If we have A[k] * 2 != A[i] + A[j],
         for any x != 0,
         (A[k] * x) * 2 = A[k] * 2 * x != (A[i] + A[j]) * x = (A[i] * x) + (A[j] * x)

         E.g: [1,3,2] * 2 = [2,6,4]


         Explanation
         With the observations above, we can easily construct any beautiful array.
         Assume we have a beautiful array A with length N

         A1 = A * 2 - 1 is beautiful with only odds from 1 to N * 2 -1
         A2 = A * 2 is beautiful with only even from 2 to N * 2
         B = A1 + A2 beautiful array with length N * 2

         E.g:

         A = [2, 1, 4, 5, 3]
         A1 = [3, 1, 7, 9, 5]
         A2 = [4, 2, 8, 10, 6]
         B = A1 + A2 = [3, 1, 7, 9, 5, 4, 2, 8, 10, 6]

         Time Complexity:
         I have iteration version here O(N)
         Naive recursion is O(NlogN)
         Recursion with one call or with cache is O(N)
     */
    int[] ans;
    public int[] beautifulArray(int N) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        while (ans.size() < N) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : ans) if (i*2-1 <= N) tmp.add(i*2-1);
            for (int i : ans) if (i*2<=N) tmp.add(i*2);
            ans = tmp;
        }
        return ans.stream().mapToInt(i->i).toArray();
    }
}
