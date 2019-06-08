package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 子集 II
 *
    给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

    说明：解集不能包含重复的子集。

    示例:

    输入: [1,2,2]
    输出:
    [
    [2],
    [1],
    [1,2,2],
    [2,2],
    [1,2],
    []
    ]

 */
public class subset_ii_90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(ans, new LinkedList<>(), nums, 0);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, int start) {
        ans.add(new ArrayList<>(tmp));
        for (int i=start; i<nums.length; i++) {
            if (i>start && nums[i]==nums[i-1]) continue;
            tmp.add(nums[i]);
            backtrack(ans, tmp, nums, i+1);
            tmp.removeLast();
        }
    }

    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        boolean[] use = new boolean[nums.length];
        helper(nums, 0, new LinkedList<>(), use);
        return ans;
    }
    private void helper(int[] nums, int start, LinkedList<Integer> tmp, boolean[] use) {
        if (start==nums.length) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        helper(nums, start+1, tmp, use);
        if (start==0 || nums[start]!=nums[start-1] || use[start-1]) {
            tmp.add(nums[start]);
            use[start] = true;
            helper(nums, start+1, tmp, use);
            tmp.removeLast();
            use[start] = false;
        }
    }
}
