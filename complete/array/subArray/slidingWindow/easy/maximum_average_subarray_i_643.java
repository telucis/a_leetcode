package complete.array.subArray.slidingWindow.easy;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 子数组最大平均数 I
 *
    给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

    示例 1:

    输入: [1,12,-5,-6,50,3], k = 4
    输出: 12.75
    解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75


    注意:

    1 <= k <= n <= 30,000。
    所给数据范围 [-10,000，10,000]。

 */
public class maximum_average_subarray_i_643 {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i=0; i<k; i++) {
            sum += nums[i];
        }
        int preSum = sum;
        for (int i=1; i<nums.length-k-1; i++) {
            int newSum = preSum - nums[i-1] + nums[i+k-1];
            sum = Math.max(newSum, sum);
            System.out.println(sum);
            preSum = newSum;
        }
        return sum*1.0/k;
    }
}
