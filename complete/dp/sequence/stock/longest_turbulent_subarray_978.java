package complete.array.mid.slideWindow;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 最长湍流子数组
 *
    当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

    若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
    或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
    也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

    返回 A 的最大湍流子数组的长度。



    示例 1：

    输入：[9,4,2,10,7,8,8,1,9]
    输出：5
    解释：(A[1] > A[2] < A[3] > A[4] < A[5])
    示例 2：

    输入：[4,8,12,16]
    输出：2
    示例 3：

    输入：[100]
    输出：1


    提示：

    1 <= A.length <= 40000
    0 <= A[i] <= 10^9

 */
public class longest_turbulent_subarray_978 {

    public int maxTurbulenceSize(int[] A) {
        int pre=0, cur=0, len=1, res=1;
        for (int i=1; i<A.length; i++) {
            cur = Integer.compare(A[i], A[i-1]);
            if (pre * cur == -1) {
                len++;
            } else if (cur == 0) {
                len = 1;
            } else {
                len = 2;
            }
            res = Math.max(res, len);
            pre = cur;
        }
        return res;
    }
}
