package complete.twoPointer.easy;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 按奇偶排序数组
 *
    给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。

    你可以返回满足此条件的任何数组作为答案。



    示例：

    输入：[3,1,2,4]
    输出：[2,4,3,1]
    输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。


    提示：

    1 <= A.length <= 5000
    0 <= A[i] <= 5000

 */
public class sort_array_by_parity_905 {

    public int[] sortArrayByParity(int[] A) {
        int left = 0, right = A.length-1;
        while (left < right) {
            while (left<right && A[left]%2 == 0) {
                left++;
            }
            while (left<right && A[right]%2==1) {
                right--;
            }
            int tmp = A[left];
            A[left] = A[right];
            A[right] = tmp;
            left++;
            right--;
        }
        return A;
    }
}
