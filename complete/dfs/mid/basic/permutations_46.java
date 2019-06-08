package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 全排列
 *
    给定一个没有重复数字的序列，返回其所有可能的全排列。

    示例:

    输入: [1,2,3]
    输出:
    [
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
    ]

 */
public class permutations_46 {

    /**
     * if (tmp.contains(nuns[i]) continue
     * backtack(ans, tmp, nums)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new LinkedList<>(), nums);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums) {
        if (tmp.size()==nums.length) ans.add(new ArrayList<>(tmp));
        else {
            for (int i=0; i<nums.length; i++) {
                if (tmp.contains(nums[i])) continue;
                tmp.add(nums[i]);
                backtrack(ans, tmp, nums);
                tmp.removeLast();
            }
        }
    }


    public List<List<Integer>> permute2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        helper(list, new LinkedList<>(), ans);
        return ans;
    }
    private void helper(List<Integer> list, LinkedList<Integer> tmp, List<List<Integer>> ans) {
        if (list.size()==0) {
            ans.add(new ArrayList<>(tmp));
        }
        for (int i=0; i<list.size(); i++) {
            int num = list.get(i);
            tmp.add(num);
            list.remove(i);
            helper(list, tmp, ans);
            tmp.removeLast();
            list.add(i, num);
        }
    }
}
