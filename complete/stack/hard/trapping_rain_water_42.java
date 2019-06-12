package complete.stack.hard;

import java.util.Stack;

/**
 * Created by telucis on 2019/6/11.
 *
 * 接雨水
 *
     给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



     上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

     示例:

     输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     输出: 6

 */
public class trapping_rain_water_42 {

    /**
     * monotone
     */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i=0; i<height.length; i++) {
            if (stack.isEmpty() || height[stack.peek()]>height[i]) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && height[stack.peek()]<=height[i]) {
                Integer index = stack.pop();
                if (stack.isEmpty()) break;
                Integer min = height[index];
                ans+=(Math.min(height[stack.peek()], height[i])-min)*(i-stack.peek()-1);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * twoPointer
     */
    public int trap2(int[] height) {
        int n = height.length;
        // left[i]表示i左边的最大值，right[i]表示i右边的最大值
        int[] left=new int[n], right=new int[n];
        for (int i=1; i<n; i++) left[i] = Math.max(left[i-1], height[i-1]);
        for (int i=n-2; i>=0; i--) right[i]=Math.max(right[i+1], height[i+1]);
        int water=0;
        for (int i=0; i<n; i++) {
            int level = Math.min(left[i], right[i]);
            water += Math.max(0, level-height[i]);
        }
        return water;
    }
}
