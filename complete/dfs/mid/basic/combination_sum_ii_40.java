package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 组合总和 II
 *
    给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的每个数字在每个组合中只能使用一次。

    说明：

    所有数字（包括目标数）都是正整数。
    解集不能包含重复的组合。
    示例 1:

    输入: candidates = [10,1,2,7,6,1,5], target = 8,
    所求解集为:
    [
    [1, 7],
    [1, 2, 5],
    [2, 6],
    [1, 1, 6]
    ]
    示例 2:

    输入: candidates = [2,5,2,1,2], target = 5,
    所求解集为:
    [
    [1,2,2],
    [5]
    ]

 */
public class combination_sum_ii_40 {

    /**
     * dfs
     * if (i>start && candidates[i]==candidates[i-1]) continue
     * backtrack(ans, tmp, candidates, target-candidates[i], i+1)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
                if (i>start && candidates[i]==candidates[i-1]) continue;
                tmp.add(candidates[i]);
                backtrack(ans, tmp, candidates, target-candidates[i], i+1);
                tmp.removeLast();
            }
        }
    }


    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] use = new boolean[candidates.length];
        helper(candidates, target, 0, new LinkedList<>(), use);
        return ans;
    }
    private void helper(int[] candidates, int target, int start, LinkedList<Integer> tmp, boolean[] use) {
        if (target==0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i] > target) {
                return;
            }
            if (i>0 && candidates[i]==candidates[i-1] && !use[i-1]) {
                continue;
            }
            tmp.add(candidates[i]);
            use[i] = true;
            helper(candidates, target-candidates[i], i+1, tmp, use);
            tmp.removeLast();
            use[i] = false;
        }
    }
}
