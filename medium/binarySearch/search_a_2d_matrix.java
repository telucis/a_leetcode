package medium.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 搜索二维矩阵
 *
    编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。
    示例 1:

    输入:
    matrix = [
    [1,   3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
    ]
    target = 3
    输出: true
    示例 2:

    输入:
    matrix = [
    [1,   3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
    ]
    target = 13
    输出: false

 */
public class search_a_2d_matrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m*n-1;
        while (left+1<right) {
            int mid = right-(right-left)/2;
            if (matrix[mid/n][mid%n]==target) {
                return true;
            } else if (matrix[mid/n][mid%n]>target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (matrix[left/n][left%n]==target) {
            return true;
        } else if (matrix[right/n][right%n]==target) {
            return true;
        }
        return false;
    }
}
