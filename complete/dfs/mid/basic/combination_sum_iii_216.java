package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 组合总和 III
 *
    找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

    说明：

    所有数字都是正整数。
    解集不能包含重复的组合。
    示例 1:

    输入: k = 3, n = 7
    输出: [[1,2,4]]
    示例 2:

    输入: k = 3, n = 9
    输出: [[1,2,6], [1,3,5], [2,3,4]]

 */
public class combination_sum_iii_216 {

    /**
     * backtrack(ans, tmp, k-1, n-i, i+1)
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        backtack(ans, new LinkedList<>(), k, n, 1);
        return ans;
    }
    private void backtack(List<List<Integer>> ans, LinkedList<Integer> tmp, int k, int n, int start) {
        if (n==0 && k==0) ans.add(new ArrayList<>(tmp));
        else if (n==0 || k==0) return;
        else {
            for (int i=start; i<10; i++) {
                if (i>n) break;
                tmp.add(i);
                backtack(ans, tmp, k-1, n-i, i+1);
                tmp.removeLast();
            }
        }
    }


    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper(k, n, 1, new LinkedList<>(), result);
        return result;
    }
    private void helper(int k, int n, int start, LinkedList<Integer> tmp, List<List<Integer>> res) {
        if (k==0) {
            if (n==0) {
                res.add(new ArrayList<>(tmp));
            }
            return;
        }
        for (int i=start; i<10; i++) {
            tmp.add(i);
            helper( k-1, n-i, i+1, tmp, res);
            tmp.removeLast();
        }
    }
}
