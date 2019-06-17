package complete.twoPointer.preSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/20
 *
 * 连续的子数组和
 *
    给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。

    示例 1:

    输入: [23,2,4,6,7], k = 6
    输出: True
    解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
    示例 2:

    输入: [23,2,6,4,7], k = 6
    输出: True
    解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
    说明:

    数组的长度不会超过10,000。
    你可以认为所有数字总和在 32 位有符号整数范围内。

 */
public class continuous_subarrsy_sum_523 {

    /**
     * preSum == dp?
     * preSum+HashMap
     * O(n)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0, -1);}};
        int runningSum = 0;
        for (int i=0; i<nums.length; i++) {
            if (k!=0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev!=null) {
                if (i-prev>1) return true;
            } else {
                map.put(runningSum, i);
            }
        }
        return false;
    }

    /**
     * O(n^2)
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        if (n<=1) return false;
        for (int i=1; i<n; i++) {
            nums[i] += nums[i-1];
            if (nums[i]==0 || (k!=0 && nums[i]%k==0)) return true;
            for (int j=0; j<i-1; j++) {
                if ((nums[i]-nums[j])==0 || k!=0 && (nums[i]-nums[j])%k==0) {
                    return true;
                }
            }
        }
        return false;
    }

}
