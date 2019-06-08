package complete.array.mooreVoting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 求众数 II
 *
    给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

    说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

    示例 1:

    输入: [3,2,3]
    输出: [3]
    示例 2:

    输入: [1,1,1,3,3,2,2,2]
    输出: [1,2]

 */
public class majority_element_ii_229 {
    /**
     * 摩尔投票法
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int x=0, y=0, cx=0, cy=0, count1=0, count2=0;
        for (Integer num : nums) {
            if ((cx==0 ||num==x) && num!=y) {
                ++cx;
                x = num;
            } else if (cy==0 || num==y) {
                ++cy;
                y = num;
            } else {
                --cx;
                --cy;
            }
        }
        for (int num : nums) {
            if (num==x) {
                ++count1;
            } else if (num==y) {
                ++count2;
            }
        }
        if (count1 > nums.length/3) {
            res.add(x);
        }
        if (count2 > nums.length/3) {
            res.add(y);
        }
        return res;
    }
}
