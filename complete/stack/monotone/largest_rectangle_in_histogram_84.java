package complete.stack.monotone;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/28
 *
 * 柱状图中最大的矩形
 *
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

    求在该柱状图中，能够勾勒出来的矩形的最大面积。





    以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。





    图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。



    示例:

    输入: [2,1,5,6,2,3]
    输出: 10

 */
public class largest_rectangle_in_histogram_84 {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0, i=0;
        int[] h = new int[heights.length+1];
        for (int j=0; j<heights.length; j++) h[j]=heights[j];
        while (i<h.length){
            if (stack.isEmpty() || h[stack.peek()]<=h[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, h[t]*(stack.isEmpty() ? i : i-stack.peek()-1));
            }
        }
        return max;
    }
}
