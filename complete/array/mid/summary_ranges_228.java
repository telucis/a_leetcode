package complete.array.mid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 汇总区间
 *
    给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

    示例 1:

    输入: [0,1,2,4,5,7]
    输出: ["0->2","4->5","7"]
    解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
    示例 2:

    输入: [0,2,3,4,6,8,9]
    输出: ["0","2->4","6","8->9"]
    解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。

 */
public class summary_ranges_228 {

    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        if (nums.length <1) {
            return ans;
        }
        ans.add(String.valueOf(nums[0]));
        if (nums.length == 1) {
            return ans;
        }
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != nums[i-1]+1) {
                String pre = ans.get(ans.size()-1);
                String cur = String.valueOf(nums[i-1]);
                String aft = String.valueOf(nums[i]);
                if (pre.equals(cur)) {
                    ans.add(aft);
                } else {
                    ans.set(ans.size()-1, pre+"->"+cur);
                    ans.add(aft);
                }
            }
        }
        if (nums[n-1] == nums[n-2]+1) {
            ans.set(ans.size()-1, ans.get(ans.size()-1)+"->"+String.valueOf(nums[n-1]));
        }
        return ans;
    }
}
