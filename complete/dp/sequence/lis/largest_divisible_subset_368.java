package complete.dp.sequence.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/18
 *
 * 最大整除子集
 *
    给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。

    如果有多个目标子集，返回其中任何一个均可。



    示例 1:

    输入: [1,2,3]
    输出: [1,2] (当然, [1,3] 也正确)
    示例 2:

    输入: [1,2,4,8]
    输出: [1,2,4,8]

 */
public class largest_divisible_subset_368 {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int[][] dp = new int[n][3];
        for (int i=0; i<nums.length; i++) {
            dp[i] = new int[]{1, i, i};
        }
        int max = 1;
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i]%nums[j]==0 && dp[j][0]+1>dp[i][0]) {
                    dp[i][0] = dp[j][0]+1;
                    dp[i][1] = j;
                    max = Math.max(max, dp[i][0]);
                }
            }
        }
        for (int i=0; i<dp.length; i++) {
            if (dp[i][0]==max) {
                int[] peek = dp[i];
                while (peek[1] != peek[2]) {
                    ans.add(nums[peek[2]]);
                    peek = dp[peek[1]];
                }
                ans.add(nums[peek[2]]);
                break;
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
