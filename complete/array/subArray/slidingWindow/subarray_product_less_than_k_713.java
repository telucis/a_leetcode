package complete.array.subArray.slidingWindow;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 乘积小于K的子数组
 *
    给定一个正整数数组 nums。

    找出该数组内乘积小于 k 的连续的子数组的个数。

    示例 1:

    输入: nums = [10,5,2,6], k = 100
    输出: 8
    解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
    需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
    说明:

    0 < nums.length <= 50000
    0 < nums[i] < 1000
    0 <= k < 10^6

 */
public class subarray_product_less_than_k_713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k==0) {
            return 0;
        }
        int cnt = 0;
        int pro = 1;
        for (int i=0, j=0; j<nums.length; j++) {
            pro *= nums[j];
            while (i<=j && pro>=k) {
                pro /= nums[i++];
            }
            cnt += j-i+1;
        }
        return cnt;
    }
}