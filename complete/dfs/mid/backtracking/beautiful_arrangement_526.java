package complete.dfs.mid.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 优美的排列
 *
    假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

    第 i 位的数字能被 i 整除
    i 能被第 i 位上的数字整除
    现在给定一个整数 N，请问可以构造多少个优美的排列？

    示例1:

    输入: 2
    输出: 2
    解释:

    第 1 个优美的排列是 [1, 2]:
    第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
    第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

    第 2 个优美的排列是 [2, 1]:
    第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
    第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
    说明:

    N 是一个正整数，并且不会超过15。

 */
public class beautiful_arrangement_526 {

    /**
     * permutations
     * if (used[i]==0 && (i%pos==0 || pos$i==0))
     * dfs(N, pos+1, used)
     */
    int count = 0;
    public int countArrangement(int N) {
        if (N==0) return 0;
        dfs(N, 1, new int[N+1]);
        return count;
    }
    private void dfs(int N, int pos, int[] used) {
        if (pos>N)  count++;
        else {
            for (int i=1; i<=N; i++) {
                if (used[i]==0 && (i%pos==0 || pos%i==0)) {
                    used[i]=1;
                    dfs(N, pos+1, used);
                    used[i]=0;
                }
            }
        }
    }

    /**
     * permutation
     */
    int ans = 0;
    public int countArrangement2(int N) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<N; i++) list.add(i+1);
        helper(list, new LinkedList<>(), N);
        return ans;
    }
    private void helper(List<Integer> list, LinkedList<Integer> tmp, int N) {
        if (tmp.size()==N) {
            ans++;
            return;
        }
        for (int i=0; i<list.size(); i++) {
            int num = list.get(i);
            int index = tmp.size()+1;
            if (num%index==0 || index%num==0) {
                tmp.add(num);
                list.remove(i);
                helper(list, tmp, N);
                tmp.removeLast();
                list.add(i, num);
            }
        }
    }
}
