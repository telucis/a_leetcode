package complete.hashmap.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 两个数组的交集

    给定两个数组，编写一个函数来计算它们的交集。

    示例 1:

    输入: nums1 = [1,2,2,1], nums2 = [2,2]
    输出: [2]
    示例 2:

    输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    输出: [9,4]
    说明:

    输出结果中的每个元素一定是唯一的。
    我们可以不考虑输出结果的顺序。

 */
public class intersection_of_two_arrays_349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
                set.remove(num);
            }
        }
        int[] result = new int[res.size()];
        for (int i=0; i<res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
