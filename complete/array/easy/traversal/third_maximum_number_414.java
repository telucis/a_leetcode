package complete.array.easy.traversal;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 第三大的数
 *
    给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

    示例 1:

    输入: [3, 2, 1]

    输出: 1

    解释: 第三大的数是 1.
    示例 2:

    输入: [1, 2]

    输出: 2

    解释: 第三大的数不存在, 所以返回最大的数 2 .
    示例 3:

    输入: [2, 2, 3, 1]

    输出: 1

    解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
    存在两个值为2的数，它们都排第二。

 */
public class third_maximum_number_414 {

    public int thirdMax(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        Integer max1 = null, max2 = null, max3 = null;
        for (int i=0; i<nums.length; i++) {
            if (max1 == null) {
                max1 = nums[i];
            } else if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] < max1) {
                if (max2 == null) {
                    max2 = nums[i];
                } else if (nums[i] > max2) {
                    max3 = max2;
                    max2 = nums[i];
                } else if (nums[i] < max2){
                    if (max3 == null || nums[i] > max3) {
                        max3 = nums[i];
                    }
                }
            }
        }
        if (max3 == null) {
            return max1;
        }
        return max3;
    }
}
