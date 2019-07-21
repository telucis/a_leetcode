package complete.array.sort;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 有效三角形的个数
 *
    给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

    示例 1:

    输入: [2,2,3,4]
    输出: 3
    解释:
    有效的组合是:
    2,3,4 (使用第一个 2)
    2,3,4 (使用第二个 2)
    2,2,3
    注意:

    数组长度不超过1000。
    数组里整数的范围为 [0, 1000]。

 */
public class valid_triangle_number_611 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0, n =nums.length;
        for (int i=n-1; i>=2; i--) {
            int l = 0, r = i-1;
            while (l<r) {
                if (nums[l]+nums[r] > nums[i]) {
                    count += r-l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return count;
    }
}
