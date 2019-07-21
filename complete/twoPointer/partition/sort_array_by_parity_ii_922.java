package complete.twoPointer.partition;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 按奇偶排序数组ii
 *
    给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

    对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

    你可以返回任何满足上述条件的数组作为答案。

    示例：

    输入：[4,2,5,7]
    输出：[4,5,2,7]
    解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。

    提示：

    2 <= A.length <= 20000
    A.length % 2 == 0
    0 <= A[i] <= 1000

 */
public class sort_array_by_parity_ii_922 {

    public int[] sortArrayByParityII(int[] A) {
        if (A.length < 2) {
            return A;
        }
        int even = 0, odd = 1;
        while (even < A.length && odd < A.length) {
            while (A[even]%2 == 0) {
                even += 2;
                if (even >= A.length) {
                    return A;
                }
            }
            while (A[odd]%2 == 1) {
                odd += 2;
                if (odd >= A.length) {
                    return A;
                }
            }
            int tmp = A[even];
            A[even] = A[odd];
            A[odd] = A[even];
            even += 2;
            odd += 2;
        }
        return A;
    }
}
