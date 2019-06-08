package complete.dfs.mid.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/18
 *
 * 连续差相同的数字
 *
    返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。

    请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。

    你可以按任何顺序返回答案。



    示例 1：

    输入：N = 3, K = 7
    输出：[181,292,707,818,929]
    解释：注意，070 不是一个有效的数字，因为它有前导零。
    示例 2：

    输入：N = 2, K = 1
    输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]


    提示：

    1 <= N <= 9
    0 <= K <= 9

 */
public class numbers_with_same_consecutive_967 {
    /**
     * iterative
     */
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> cur = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        for (int i=2; i<=N; i++) {
            List<Integer> cur2=new ArrayList<>();
            for (int x : cur) {
                int y=x%10;
                if (x>0 && y+K<10) cur2.add(x*10+y+K);
                if (x>0 && K>0 && y-K>=0) cur2.add(x*10+y-K);
            }
            cur = cur2;
        }
        return cur.stream().mapToInt(a->a).toArray();
    }


    /**
     * dfs
     */
    public int[] numsSameConsecDiff2(int N, int K) {
        List<Integer> list = new ArrayList<>();
        for (int i=N==1?0:1; i<=9; i++) {
            helper(N-1, K, i, list);
        }
        return list.stream().mapToInt(a->a).toArray();
    }
    private void helper(int n, int k, int tmp, List<Integer> list) {
        if (n==0) {
            list.add(tmp);
            return;
        }
        for (int i=0; i<10; i++) {
            if (k==Math.abs(tmp%10-i)) {
                helper(n-1, k, tmp*10+i, list);
            }
        }
    }
}
