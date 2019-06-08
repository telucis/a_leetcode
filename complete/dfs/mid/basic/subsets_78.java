package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 子集
 *
    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

    说明：解集不能包含重复的子集。

    示例:

    输入: nums = [1,2,3]
    输出:
    [
    [3],
    [1],
    [2],
    [1,2,3],
    [1,3],
    [2,3],
    [1,2],
    []
    ]

 */
public class subsets_78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new LinkedList<>(), nums, 0);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, int start) {
        ans.add(new ArrayList<>(tmp));
        for (int i=start; i<nums.length; i++) {
            tmp.add(nums[i]);
            backtrack(ans, tmp, nums, i+1);
            tmp.removeLast();
        }
    }


    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void helper(int[] nums, int start, List<Integer> tmp, List<List<Integer>> result) {
        int n = nums.length;
        if (n == start) {
            result.add(tmp);
            return;
        }
        List<Integer> newTmp = new ArrayList<>();
        for (Integer i : tmp) {
            newTmp.add(i);
        }
        newTmp.add(nums[start]);
        helper(nums, start+1, tmp, result);
        helper(nums, start+1, newTmp, result);
    }
}
