package complete.dfs.hard.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/24
 *
 * N皇后
 *
    n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



    上图为 8 皇后问题的一种解法。

    给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

    每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

    示例:

    输入: 4
    输出: [
    [".Q..",  // 解法 1
    "...Q",
    "Q...",
    "..Q."],

    ["..Q.",  // 解法 2
    "Q...",
    "...Q",
    ".Q.."]
    ]
    解释: 4 皇后问题存在两个不同的解法。

 */
public class n_queens_51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<char[]> tmp = new ArrayList<>();
        for (int i=0;i<n; i++) {
            char[] sb = new char[n];
            for (int j=0; j<n; j++) sb[i]='.';
            tmp.add(sb);
        }
        process(ans, tmp, 0, n);
        return ans;
    }
    private void process(List<List<String>> ans, List<char[]> tmp, int start, int n) {
        if (start==n) {
            List<String> res = new ArrayList<>();
            for (char[] t : tmp) res.add(new String(t));
            ans.add(res);
        }
        else {
            for (int i=0; i<n; i++) {
                if (valid(tmp, start, i)) {
                    tmp.get(start)[i]='Q';
                    process(ans, tmp, start+1, n);
                    tmp.get(start)[i]='.';
                    System.out.println(new String(tmp.get(start)));
                }
            }
        }
    }
    private boolean valid(List<char[]> res, int x, int y) {
        int n = res.size();
        for (int i=0; i<n; i++) if (res.get(i)[y]=='Q') return false;
        for (int i=0; x+i<n && y+i<n; i++) if (res.get(i+x)[y+i]=='Q') return false;
        for (int i=0; x+i<n && y-i>=0; i++) if (res.get(i+x)[y-i]=='Q') return false;
        for (int i=0; x-i>=0 && y+i<n; i++) if (res.get(x-i)[y+i]=='Q') return false;
        for (int i=0; x-i>=0 && y-i>=0; i++) if (res.get(x-i)[y-i]=='Q') return false;
        return true;
    }
}
