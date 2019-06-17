package complete.twoPointer.easy;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 有序数组的平方
 *
    给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

    示例 1：

    输入：[-4,-1,0,3,10]
    输出：[0,1,9,16,100]
    示例 2：

    输入：[-7,-3,2,3,11]
    输出：[4,9,9,49,121]


    提示：

    1 <= A.length <= 10000
    -10000 <= A[i] <= 10000
    A 已按非递减顺序排序。

 */
public class squares_of_a_sorted_array_977 {

    /**
     * 反向双指针
     */
    public int[] sortedSquares(int[] A) {
        int left = 0, right = A.length-1;
        int index = right;
        int[] res = new int[A.length];
        while (left<=right) {
            int x = A[left] * A[left];
            int y = A[right] * A[right];
            if (x > y) {
                res[index] = x;
                left++;
            } else {
                res[index] = y;
                right--;
            }
            index--;
        }
        return res;
    }
}
