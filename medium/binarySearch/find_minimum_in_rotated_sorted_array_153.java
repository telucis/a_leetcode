package medium.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 寻找旋转排序数组中的最小值
 *
    假设按照升序排序的数组在预先未知的某个点上进行了旋转。

    ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

    请找出其中最小的元素。

    你可以假设数组中不存在重复元素。

    示例 1:

    输入: [3,4,5,1,2]
    输出: 1
    示例 2:

    输入: [4,5,6,7,0,1,2]
    输出: 0

 */
public class find_minimum_in_rotated_sorted_array_153 {

    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while (left+1<right) {
            int mid = right-(right-left)/2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] < nums[right]) {
            return nums[left];
        } else {
            return nums[right];
        }
    }
}
