package medium.stack;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 子数组的最小值之和
 *
    给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。

    由于答案可能很大，因此返回答案模 10^9 + 7。



    示例：

    输入：[3,1,2,4]
    输出：17
    解释：
    子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
    最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。


    提示：

    1 <= A <= 30000
    1 <= A[i] <= 30000

 */
public class sum_of_subarray_minimums_907 {

    public int sumSubarrayMins(int[] A) {
        int res=0, n=A.length, mod=(int)1e9+7;
        int[] left=new int[n], right=new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i=0; i<n; i++) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0]>A[i]) {
                count += s1.pop()[1];
            }
            s1.push(new int[]{A[i], count});
            left[i] = count;
        }

        for (int i=n-1; i>=0; i--) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0]>=A[i]) {
                count += s2.pop()[1];
            }
            s2.push(new int[]{A[i], count});
            right[i] = count;
        }
        for (int i=0; i<n; i++) {
            res = (res+A[i]*left[i]*right[i]) % mod;
        }
        return res;
    }
}
