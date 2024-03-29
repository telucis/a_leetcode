package complete.math.mid;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/20
 *
 * 最少移动次数使数组元素相等 II
 *
    给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。

    例如:

    输入:
    [1,2,3]

    输出:
    2

    说明：
    只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：

    [1,2,3]  =>  [2,2,3]  =>  [2,2,2]

 */
public class minimum_moves_to_equal_array_elements_ii_462 {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, l=0, r=n-1;
        int ans = 0;
        while (l<r) {
            ans += nums[r]-nums[l];
            l++;
            r--;
        }
        return ans;
    }
}
