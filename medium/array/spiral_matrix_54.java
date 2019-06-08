package medium.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 螺旋矩阵
 *
    给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

    示例 1:

    输入:
    [
    [ 1, 2, 3 ],
    [ 4, 5, 6 ],
    [ 7, 8, 9 ]
    ]
    输出: [1,2,3,6,9,8,7,4,5]
    示例 2:

    输入:
    [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9,10,11,12]
    ]
    输出: [1,2,3,4,8,12,11,10,9,5,6,7]

 */
public class spiral_matrix_54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length==0 || matrix[0].length==0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        int dx=0, dy=1, x=0, y=0;
        while (res.size() < n*m) {
            res.add(matrix[x][y]);
            matrix[x][y] = 0;
            if (x+dx<0 || x+dx>=m || y+dy<0 || y+dy>=n || matrix[x+dx][y+dy]==0) {
                int tmp = dx;
                dx = dy;
                dy = -tmp;
            }
            x += dx;
            y += dy;
        }
        return res;
    }
}
