package medium.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 *
    给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

    你的算法时间复杂度必须是 O(log n) 级别。

    如果数组中不存在目标值，返回 [-1, -1]。

    示例 1:

    输入: nums = [5,7,7,8,8,10], target = 8
    输出: [3,4]
    示例 2:

    输入: nums = [5,7,7,8,8,10], target = 6
    输出: [-1,-1]

 */
public class find_first_and_last_position_of_element_in_sorted_array_34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length==0) {
            return new int[]{-1, -1};
        }
        int left = 0, right = nums.length-1;
        int[] res = new int[2];
        while (left+1<right) {
            int mid = right-(right-left)/2;
            if (nums[mid]>=target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left]!=target && nums[right]!=target) {
            return new int[]{-1, -1};
        }
        res[0] = nums[left]==target ? left : right;
        left = res[0];
        right = nums.length-1;
        while (left+1<right) {
            int mid = right-(right-left)/2;
            if (nums[mid]>target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        res[1] = nums[right]==target ? right : left;
        return res;
    }
}
