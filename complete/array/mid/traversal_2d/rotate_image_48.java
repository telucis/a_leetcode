package complete.array.mid.traversal_2d;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 旋转图像
 *
    给定一个 n × n 的二维矩阵表示一个图像。

    将图像顺时针旋转 90 度。

    说明：

    你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

    示例 1:

    给定 matrix =
    [
    [1,2,3],
    [4,5,6],
    [7,8,9]
    ],

    原地旋转输入矩阵，使其变为:
    [
    [7,4,1],
    [8,5,2],
    [9,6,3]
    ]
    示例 2:

    给定 matrix =
    [
    [ 5, 1, 9,11],
    [ 2, 4, 8,10],
    [13, 3, 6, 7],
    [15,14,12,16]
    ],

    原地旋转输入矩阵，使其变为:
    [
    [15,13, 2, 5],
    [14, 3, 4, 1],
    [12, 6, 8, 9],
    [16, 7,10,11]
    ]

 */
public class rotate_image_48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int layer = 0;
        while (layer<n/2) {
            for (int i=layer; i<n-layer-1; i++) {
                int tmp = matrix[layer][i];
                matrix[layer][i] = matrix[n-1-i][layer];
                matrix[n-1-i][layer] = matrix[n-layer-1][n-1-i];
                matrix[n-layer-1][n-1-i] = matrix[i][n-layer-1];
                matrix[i][n-layer-1] = tmp;
            }
            layer++;
        }
    }
}
