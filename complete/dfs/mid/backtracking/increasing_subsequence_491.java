package complete.dfs.mid.backtracking;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 递增子序列
 *
    给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。

    示例:

    输入: [4, 6, 7, 7]
    输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
    说明:

    给定数组的长度不会超过15。
    数组中的整数范围是 [-100,100]。
    给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。

 */
public class increasing_subsequence_491 {

    /**
     * combinations
     * if (!set.contains(nums[i])
     * dfs(res, tmp, nums, index)
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(new LinkedList<>(), 0, nums, res);
        return res;
    }
    private void dfs(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res) {
        if (list.size()>1) res.add(new LinkedList<>(list));
        Set<Integer> used = new HashSet<>();
        for (int i=index; i<nums.length; i++) {
            if (used.contains(nums[i])) continue;
            if (list.size()==0 || nums[i]>=list.peekLast()) {
                used.add(nums[i]);
                list.add(nums[i]);
                dfs(list, i+1, nums, res);
                list.removeLast();
            }
        }
    }

    // wrong solution
    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] use = new boolean[nums.length];
        helper(nums, 0, new LinkedList<>(), ans, use);
        return ans;
    }
    private void helper(int[] nums, int start, LinkedList<Integer> tmp, List<List<Integer>> ans, boolean[] use) {
        if (start==nums.length) {
            if (tmp.size()>=2) ans.add(new ArrayList<>(tmp));
            return;
        }
        helper(nums, start+1, tmp, ans, use);
        if (tmp.isEmpty() || tmp.get(tmp.size()-1)<=nums[start]) {
            if (start==0 || nums[start]!=nums[start-1] || use[start-1]) {
                tmp.add(nums[start]);
                use[start] = true;
                helper(nums, start+1, tmp, ans, use);
                tmp.removeLast();
                use[start] = false;
            }
        }
    }
}
