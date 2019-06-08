package complete.dfs.hard.backtrack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author karl.wy
 * @date 2019/05/25
 *
 * 正方形数组的数目
 *
    给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。

    返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。



    示例 1：

    输入：[1,17,8]
    输出：2
    解释：
    [1,8,17] 和 [17,8,1] 都是有效的排列。
    示例 2：

    输入：[2,2,2]
    输出：1


    提示：

    1 <= A.length <= 12
    0 <= A[i] <= 1e9

 */
public class number_of_squareful_arrays_996 {
    int ans = 0;
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        helper(new LinkedList<>(), A, new boolean[A.length]);
        return ans;
    }
    private void helper(LinkedList<Integer> tmp, int[] A, boolean[] used) {
        if (tmp.size()==A.length) ans++;
        else {
            for (int i=0; i<A.length; i++) {
                if (used[i] || i>0 && A[i]==A[i-1] && !used[i-1]) continue;
                if (tmp.size()>0 && !isSquare(A[i], tmp.peekLast())) continue;
                used[i]=true;
                tmp.add(A[i]);
                helper(tmp, A, used);
                used[i]=false;
                tmp.removeLast();
            }
        }
    }
    private boolean isSquare(int a, int b) {
        double sqr = Math.sqrt(a+b);
        return (sqr-Math.floor(sqr))==0;
    }
}
