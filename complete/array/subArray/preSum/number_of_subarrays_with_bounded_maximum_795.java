package complete.array.mid.preSum;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 区间子数组个数
 *
    给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。

    求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。

    例如 :
    输入:
    A = [2, 1, 4, 3]
    L = 2
    R = 3
    输出: 3
    解释: 满足条件的子数组: [2], [2, 1], [3].
    注意:

    L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
    数组 A 的长度范围在[1, 50000]。

 */
public class number_of_subarrays_with_bounded_maximum_795 {

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return numSubarrayBoundedMax(A, R) - numSubarrayBoundedMax(A, L-1);
    }
    private int numSubarrayBoundedMax(int[] A, int max) {
        int res = 0;
        int numSubarray = 0;
        for (int num : A) {
            if (num <= max) {
                numSubarray++;
                res += numSubarray;
            } else {
                numSubarray = 0;
            }
        }
        return res;
    }
}
