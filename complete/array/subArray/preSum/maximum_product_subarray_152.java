package complete.array.subArray.preSum;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 乘积最大子序列
 *
    给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

    示例 1:

    输入: [2,3,-2,4]
    输出: 6
    解释: 子数组 [2,3] 有最大乘积 6。
    示例 2:

    输入: [-2,0,-1]
    输出: 0
    解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

 */
public class maximum_product_subarray_152 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax=1, imin=1;
        for (int i=0; i<nums.length; i++) {
            //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }
}
