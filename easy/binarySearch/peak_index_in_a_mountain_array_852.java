package easy.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/16
 *
 * 山脉数组的峰顶索引
 *
    我们把符合下列属性的数组 A 称作山脉：

    A.length >= 3
    存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
    给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。



    示例 1：

    输入：[0,1,0]
    输出：1
    示例 2：

    输入：[0,2,1,0]
    输出：1


    提示：

    3 <= A.length <= 10000
    0 <= A[i] <= 10^6
    A 是如上定义的山脉
 */
public class peak_index_in_a_mountain_array_852 {

    public int peakIndexInMountainArray(int[] A) {
        int n = A.length;
        int start = 0, end = n-1;
        while (start+1 < end) {
            int mid = start + (end-start)/2;
            if (A[mid] > A[mid+1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] > A[end]) {
            return start;
        }
        return end;
    }
}
