package complete.array.mid.traversal_2d;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 螺旋矩阵 II
 *
    给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

    示例:

    输入: 3
    输出:
    [
    [ 1, 2, 3 ],
    [ 8, 9, 4 ],
    [ 7, 6, 5 ]
    ]

 */
public class spiral_matrix_ii_59 {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int total = n * n;
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int i = 0, j = 0, c = 1, index = 0;
        while (c <= total) {
            res[i][j] = c++;
            if (!judge(n, i+dx[index], j+dy[index], res)) {
                index = index == 3 ? 0 : index+1;
            }
            i += dx[index];
            j += dy[index];
        }
        return res;
    }
    private boolean judge(int n, int x, int y, int[][] res) {
        if (x>=0 && x<n && y>=0 && y<n && res[x][y]==0) {
            return true;
        }
        return false;
    }
}
