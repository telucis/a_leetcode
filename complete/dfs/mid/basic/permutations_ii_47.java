package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 全排列 II
 *
    给定一个可包含重复数字的序列，返回所有不重复的全排列。

    示例:

    输入: [1,1,2]
    输出:
    [
    [1,1,2],
    [1,2,1],
    [2,1,1]
    ]

 */
public class permutations_ii_47 {

    /**
     * if (used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1]) continue
     * backtack(ans, tmp, nums, used)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(ans, new LinkedList<>(), nums, new boolean[nums.length]);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, boolean[] used) {
        if (tmp.size()==nums.length) ans.add(new ArrayList<>(tmp));
        else {
            for (int i=0; i<nums.length; i++) {
                if (used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
                used[i]=true;
                tmp.add(nums[i]);
                backtrack(ans, tmp, nums, used);
                used[i]=false;
                tmp.removeLast();
            }
        }
    }

    /**
     * 空间复杂度O(1)
     */
    public List<Integer> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, nums.length-1, ans);
        return ans;
    }
    private void helper(int[] nums, int left, int right, List<List<Integer>> ans) {
        if (left == right) ans.add(new ArrayList<>(nums));
        else {
            for (int i=left; i<=right; i++) {
                if (i!=left && nums[left]==nums[i]) continue;
                swap(nums, i, j);
                helper(nums, left+1, right, ans);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i : nums) list.add(i);
        helper(list, new LinkedList<>(), ans);
        return ans;
    }
    private void helper(List<Integer> list, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        if (list.size()==0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=0; i<list.size(); i++) {
            if (i>0 && list.get(i).equals(list.get(i-1))) {
                continue;
            }
            int num = list.get(i);
            tmp.add(num);
            list.remove(i);
            helper(list, tmp, ans);
            tmp.removeLast();
            list.add(i, num);
        }
    }
}
