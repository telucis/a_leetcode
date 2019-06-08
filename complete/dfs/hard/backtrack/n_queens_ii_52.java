package complete.dfs.hard.backtrack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/24
 *
 * N皇后 II
 *
    n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



    上图为 8 皇后问题的一种解法。

    给定一个整数 n，返回 n 皇后不同的解决方案的数量。

    示例:

    输入: 4
    输出: 2
    解释: 4 皇后问题存在如下两个不同的解法。
    [
    [".Q..",  // 解法 1
    "...Q",
    "Q...",
    "..Q."],

    ["..Q.",  // 解法 2
    "Q...",
    "...Q",
    ".Q.."]
    ]

 */
public class n_queens_ii_52 {

    public int totalNQueens(int n) {
        Set<Integer> column = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();
        return dfs(n, 0, column, diag1, diag2);
    }
    private int dfs(int n, int row, Set<Integer> col, Set<Integer> d1, Set<Integer> d2) {
        int count = 0;
        for (int i=0; i<n; i++) {
            if (col.contains(i)) continue;
            if (d1.contains(row+i)) continue;
            if (d2.contains(row-i)) continue;
            if (row==n-1) count++;
            else {
                col.add(i);
                d1.add(row+i);
                d2.add(row-i);
                count += dfs(n, row+1, col, d1, d2);
                col.remove(i);
                d1.remove(row+i);
                d2.remove(row-i);
            }
        }
        return count;
    }
}
