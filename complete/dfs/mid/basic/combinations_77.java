package complete.dfs.mid.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 组合
 *
    给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

    示例:

    输入: n = 4, k = 2
    输出:
    [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
    ]

 */
public class combinations_77 {

    /**
     * backtack(ans, tmp, n, k-1, i+1)
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtack(ans, new LinkedList<>(), n, k,1);
        return ans;
    }
    private void backtack(List<List<Integer>> ans, LinkedList<Integer> tmp, int n, int k, int start) {
        if (k==0) ans.add(new ArrayList<>(tmp));
        else {
            for (int i=start; i<=n; i++) {
                tmp.add(i);
                backtack(ans, tmp, n, k-1, i+1);
                tmp.removeLast();
            }
        }
    }


    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            list.add(i);
        }
        helper(list, new ArrayList<>(), 0, ans, k);
        return ans;
    }
    private void helper(List<Integer> list, List<Integer> tmp, int start, List<List<Integer>> ans, int k) {
        if (tmp.size()==k) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        if (start==list.size()) return;
        List<Integer> newTmp = new ArrayList<>(tmp);
        newTmp.add(list.get(start));
        helper(list, newTmp, start+1, ans, k);
        helper(list, tmp, start+1, ans, k);
    }
}
