package complete.array.straighforward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 数组的度
 *
    给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

    你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

    示例 1:

    输入: [1, 2, 2, 3, 1]
    输出: 2
    解释:
    输入数组的度是2，因为元素1和2的出现频数最大，均为2.
    连续子数组里面拥有相同度的有如下所示:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    最短连续子数组[2, 2]的长度为2，所以返回2.
    示例 2:

    输入: [1,2,2,3,1,4,2]
    输出: 6
    注意:

    nums.length 在1到50,000区间范围内。
    nums[i] 是一个在0到49,999范围内的整数。

 */
public class degree_of_an_array_697 {

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int max = 1;
        List<Integer> maxValue = new ArrayList<Integer>();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                map.put(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
            if (map.get(nums[i]).size() > max) {
                max = map.get(nums[i]).size();
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key).size() == max) {
                maxValue.add(key);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i=0; i<maxValue.size(); i++) {
            int key = maxValue.get(i);
            List<Integer> list = map.get(key);
            System.out.println(key);
            res = Math.min(res, list.get(list.size()-1) - list.get(0) + 1);
        }
        return res;
    }
}
