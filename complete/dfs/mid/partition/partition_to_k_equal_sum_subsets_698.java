package complete.dfs.mid.partition;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 划分为k个相等的子集
 *
    给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。

    示例 1：

    输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
    输出： True
    说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。


    注意:

    1 <= k <= len(nums) <= 16
    0 < nums[i] < 10000

 */
public class partition_to_k_equal_sum_subsets_698 {

    /**
     * partition
     *
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        for (int i : nums) sum+=i;
        if (sum%k!=0) return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum/k);

    }
    private boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target) {
        if (k==1) return true;
        if (cur_sum == target && cur_num>0) return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for (int i=start_index; i<nums.length; i++) {
            if (visited[i]==0) {
                visited[i]=1;
                if (canPartition(nums, visited, i+1, k, cur_sum+nums[i], cur_num++, target)) return true;
                visited[i]=0;
            }
        }
        return false;
    }
}
