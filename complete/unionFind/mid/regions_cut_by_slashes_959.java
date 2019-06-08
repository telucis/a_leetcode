package complete.unionFind;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 由斜杠划分区域
 *
    在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

    （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

    返回区域的数目。



    示例 1：

    输入：
    [
    " /",
    "/ "
    ]
    输出：2
    解释：2x2 网格如下：

    示例 2：

    输入：
    [
    " /",
    "  "
    ]
    输出：1
    解释：2x2 网格如下：

    示例 3：

    输入：
    [
    "\\/",
    "/\\"
    ]
    输出：4
    解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
    2x2 网格如下：

    示例 4：

    输入：
    [
    "/\\",
    "\\/"
    ]
    输出：5
    解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
    2x2 网格如下：

    示例 5：

    输入：
    [
    "//",
    "/ "
    ]
    输出：3
    解释：2x2 网格如下：



    提示：

    1 <= grid.length == grid[0].length <= 30
    grid[i][j] 是 '/'、'\'、或 ' '。

 */
public class regions_cut_by_slashes_959 {
    int count, n;
    int[] f;
    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        f = new int[n*n*4];
        count = n*n*4;
        for (int i=0; i<count; i++) {
            f[i] = i;
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i>0) {
                    union(g(i-1,j,2), g(i,j,0));
                }
                if (j>0) {
                    union(g(i,j-1,1), g(i,j,3));
                }
                if (grid[i].charAt(j)!='/') {
                    union(g(i,j,0), g(i,j,1));
                    union(g(i,j,2), g(i,j,3));
                }
                if (grid[i].charAt(j)!='\\') {
                    union(g(i,j,0), g(i,j,3));
                    union(g(i,j,1), g(i,j,2));
                }
            }
        }
        return count;
    }
    private int find(int x) {
        if (x != f[x]) {
            f[x] = find(f[x]);
        }
        return f[x];
    }
    private void union(int x, int y) {
        x = find(x); y = find(y);
        if (x!=y) {
            f[x] = y;
            count--;
        }
    }
    private int g(int i, int j, int k) {
        return (i*n+j)*4+k;
    }
}
