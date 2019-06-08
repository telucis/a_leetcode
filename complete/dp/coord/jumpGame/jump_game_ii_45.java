package complete.dp.coord.jumpGame;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/28
 *
 * 跳跃游戏 II
 *
    给定一个非负整数数组，你最初位于数组的第一个位置。

    数组中的每个元素代表你在该位置可以跳跃的最大长度。

    你的目标是使用最少的跳跃次数到达数组的最后一个位置。

    示例:

    输入: [2,3,1,1,4]
    输出: 2
    解释: 跳到最后一个位置的最小跳跃数是 2。
    从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
    说明:

    假设你总是可以到达数组的最后一个位置。


 */
public class jump_game_ii_45 {
    /**
     * greedy
     */
    public int jump(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n = nums.length, start=0, end=0, jumps=0;
        while (end<nums.length-1) {
            jumps++;
            int farthest = end;
            for (int i=start; i<=end; i++) {
                if (nums[i]+i>farthest) farthest=nums[i]+i;
            }
            start=end+1;
            end=farthest;
        }
        return jumps;
    }

    /**
     * dp
     * dp[nums.len]
     * for(nums.len) for(nums[i]) dp[i+j]=Math.min(dp[i+j], dp[i]+1)
     */
    public int jump_dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for (int i=0; i<n; i++) {
            if (dp[i]==Integer.MAX_VALUE) return -1;
            for (int j=1; j<=nums[i]; j++) {
                if (i+j<n) dp[i+j] = Math.min(dp[i+j], dp[i]+1);
                if (i+j==n-1) return dp[n-1];
            }
        }
        return dp[n-1];
    }
}
