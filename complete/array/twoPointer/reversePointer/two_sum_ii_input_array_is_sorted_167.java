package complete.twoPointer.reversePointer;

/**
 * @author karl.wy
 * @date 2019/04/16
 *
 * 两数之和 II - 输入有序数组
 *
    给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

    函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

    说明:

    返回的下标值（index1 和 index2）不是从零开始的。
    你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
    示例:

    输入: numbers = [2, 7, 11, 15], target = 9
    输出: [1,2]
    解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

 */
public class two_sum_ii_input_array_is_sorted_167 {

    /**
     * 二分
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i=0; i<n; i++) {
            int t = target - numbers[i];
            int left = i+1, right = n-1;
            while (left+1<right) {
                int mid = left + (right-left)/2;
                if (numbers[mid] == t) {
                    return new int[]{i+1, mid+1};
                } else if(numbers[mid] > t) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (numbers[left] == t) {
                return new int[]{i+1, left+1};
            } else if (numbers[right] == t) {
                return new int[]{i+1, right+1};
            }
        }
        return new int[]{0, 0};
    }

    /**
     * 双指针
     */
    public int[] twoSum2(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0, right = n-1;
        while (left<right) {
            int tmp = numbers[left] + numbers[right];
            if (tmp == target) {
                return new int[]{left+1, right+1};
            } else if (tmp > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{0, 0};
    }
}
