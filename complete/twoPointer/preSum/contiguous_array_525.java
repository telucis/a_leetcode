package complete.twoPointer.preSum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by telucis on 2019/5/12.
 *
 * 连续数组
 *
     给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。



     示例 1:

     输入: [0,1]
     输出: 2
     说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
     示例 2:

     输入: [0,1,0]
     输出: 2
     说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。


     注意: 给定的二进制数组的长度不会超过50000。


 */
public class contiguous_array_525 {

    public int findMaxLength(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            if (nums[i]==0) nums[i]=-1;
        }
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum=0, max=0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i-sumToIndex.get(sum));
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return max;
    }
}
