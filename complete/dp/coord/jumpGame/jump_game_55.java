package complete.dp.coord.jumpGame;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 跳跃游戏
 *
    给定一个非负整数数组，你最初位于数组的第一个位置。

    数组中的每个元素代表你在该位置可以跳跃的最大长度。

    判断你是否能够到达最后一个位置。

    示例 1:

    输入: [2,3,1,1,4]
    输出: true
    解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
    示例 2:

    输入: [3,2,1,0,4]
    输出: false
    解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

 */
public class jump_game_55 {
    /**
     * dp
     * dp[nums.len]
     * for(nums.len) for(i+1, i+num[i]) dp[j]=true
     */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i=0; i<nums.length; i++) {
            if (dp[i]) {
                for (int j=i+1; j<=(i+nums[i]>=nums.length ? nums.length-1 : i+nums[i]); j++) {
                    dp[j] = true;
                }
            }
            if (dp[nums.length-1]) {
                return true;
            }
        }
        return dp[nums.length-1];
    }
    /**
     * greedy
     */
    public boolean canJump2(int[] nums) {
        int len = 1;
        for (int i=0; i<len; i++) {
            int newLen = Math.max(i+nums[i]+1, len);
            if (newLen >= nums.length) {
                return true;
            }
            len = newLen;
        }
        return false;
    }
}
