package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 组合总和
 *
    给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的数字可以无限制重复被选取。

    说明：

    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。
    示例 1:

    输入: candidates = [2,3,6,7], target = 7,
    所求解集为:
    [
    [7],
    [2,2,3]
    ]
    示例 2:

    输入: candidates = [2,3,5], target = 8,
    所求解集为:
    [
    [2,2,2,2],
    [2,3,3],
    [3,5]
    ]

 */
public class combination_sum_39 {

    /**
     * dfs
     * N叉树先序遍历
     * backtrack(ans, tmp, candidates, target-candidates[i], i)
     * LinkedList做回溯状态
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(ans, new LinkedList<>(), candidates, target, 0);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] candidates, int target, int start) {
        if (target==0) ans.add(new ArrayList<>(tmp));
        else {
            for (int i=start; i<candidates.length; i++) {
                if (candidates[i]>target) break;
                tmp.add(candidates[i]);
                backtrack(ans, tmp, candidates, target-candidates[i], i);
                tmp.removeLast();
            }
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, new LinkedList<>(), result);
        return result;
    }
    private void helper(int[] candidates, int target, int start, LinkedList<Integer> tmp, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i] > target) {
                return;
            }
            tmp.add(candidates[i]);
            helper(candidates, target-candidates[i], i, tmp, res);
            tmp.removeLast();
        }
    }
}
