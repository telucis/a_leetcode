package medium.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/29
 *
 * 图像重叠
 *
    给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。

    我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。

    （请注意，转换不包括向任何方向旋转。）

    最大可能的重叠是什么？

    示例 1:

    输入：A = [[1,1,0],
    [0,1,0],
    [0,1,0]]
    B = [[0,0,0],
    [0,1,1],
    [0,0,1]]
    输出：3
    解释: 将 A 向右移动一个单位，然后向下移动一个单位。
    注意:

    1 <= A.length = A[0].length = B.length = B[0].length <= 30
    0 <= A[i][j], B[i][j] <= 1

 */
public class image_overlap_835 {

    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        List<Integer> LA = new ArrayList<>();
        List<Integer> LB = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i=0; i<N*N; i++) {
            if (A[i/N][i%N]==1) {
                LA.add(i/N*100+i%N);
            }
            if (B[i/N][i%N]==1) {
                LB.add(i/N*100+i%N);
            }
        }
        for (int i : LA) {
            for (int j : LB) {
                count.put(i-j, count.getOrDefault(i-j, 0)+1);
            }
        }
        int res = 0;
        for (int i: count.values()) {
            res = Math.max(res, i);
        }
        return res;
    }
}
