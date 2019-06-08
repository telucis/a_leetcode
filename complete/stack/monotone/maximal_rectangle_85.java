package complete.stack.monotone;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/28
 *
 * 最大矩形
 *
    给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

    示例:

    输入:
    [
    ["1","0","1","0","0"],
    ["1","0","1","1","1"],
    ["1","1","1","1","1"],
    ["1","0","0","1","0"]
    ]
    输出: 6

 */
public class maximal_rectangle_85 {

    public int maximalRectangle(char[][] matrix) {
        int m=matrix.length, n=m==0 ? 0 : matrix[0].length;
        int[][] height = new int[m][n+1];

        int maxArea = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j]=='0') height[i][j]=0;
                else height[i][j] = i==0 ? 1 : height[i-1][j]+1;
            }
        }
        for (int i=0; i<m; i++) {
            int area = maxAreaInHist(height[i]);
            if (area > maxArea) maxArea = area;
        }
        return maxArea;
    }
    private int maxAreaInHist(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int i=0, max=0;
        while (i<height.length) {
            if (stack.isEmpty() || height[stack.peek()]<=height[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, height[t] * (stack.isEmpty() ? i : i-stack.peek()-1));
            }
        }
        return max;
    }
}
