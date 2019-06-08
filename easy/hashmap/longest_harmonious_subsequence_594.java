package easy.hashmap;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 最长和谐子序列
 *
    和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。

    现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。

    示例 1:

    输入: [1,3,2,2,5,2,3,7]
    输出: 5
    原因: 最长的和谐数组是：[3,2,2,2,3].
    说明: 输入的数组长度最大不超过20,000.


 */
public class longest_harmonious_subsequence_594 {

    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (Integer key : map.keySet()) {
            int sum = map.get(key);
            if (map.containsKey(key+1)) {
                sum += map.get(key+1);
            } else {
                continue;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
