package medium.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 搜索旋转排序数组
 *
    假设按照升序排序的数组在预先未知的某个点上进行了旋转。

    ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

    你可以假设数组中不存在重复的元素。

    你的算法时间复杂度必须是 O(log n) 级别。

    示例 1:

    输入: nums = [4,5,6,7,0,1,2], target = 0
    输出: 4
    示例 2:

    输入: nums = [4,5,6,7,0,1,2], target = 3
    输出: -1

 */
public class search_in_rotated_sorted_array_33 {
    public int search(int[] nums, int target) {
        if (nums.length==0) {
            return -1;
        }
        int left = 0, right = nums.length-1;
        while (left+1 < right) {
            int mid = right-(right-left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[nums.length-1]) {
                if (target>nums[mid] && nums[mid]>nums[nums.length-1]) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                if (target<nums[mid] && nums[mid]<nums[nums.length-1]) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        if (nums[left]==target) {
            return left;
        } else if (nums[right]==target) {
            return right;
        }
        return -1;
    }
}
