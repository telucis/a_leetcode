package medium.stack;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 132模式
 *
    给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。

    注意：n 的值小于15000。

    示例1:

    输入: [1, 2, 3, 4]

    输出: False

    解释: 序列中不存在132模式的子序列。
    示例 2:

    输入: [3, 1, 4, 2]

    输出: True

    解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
    示例 3:

    输入: [-1, 3, 2, 0]

    输出: True

    解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].

 */
public class one32_pattern_456 {

    /**
     The idea is that we can use a stack to keep track of previous min-max intervals.

     Here is the principle to maintain the stack:

     For each number num in the array

     If stack is empty:

     push a new Pair of num into stack
     If stack is not empty:

     if num < stack.peek().min, push a new Pair of num into stack

     if num >= stack.peek().min, we first pop() out the peek element, denoted as last

     if num < last.max, we are done, return true;

     if num >= last.max, we merge num into last, which means last.max = num.
     Once we update last, if stack is empty, we just push back last.
     However, the crucial part is:
     If stack is not empty, the updated last might:

     Entirely covered stack.peek(), i.e. last.min < stack.peek().min (which is always true) && last.max >= stack.peek().max, in which case we keep popping out stack.peek().
     Form a 1-3-2 pattern, we are done ,return true
     So at any time in the stack, non-overlapping Pairs are formed in descending order by their min value, which means the min value of peek element in the stack is always the min value globally.
     */
    public boolean find132pattern(int[] nums) {
        Stack<int[]> stack = new Stack<>();
        for (int n : nums) {
            if (stack.isEmpty() || n<stack.peek()[0]) stack.push(new int[]{n, n});
            else if (n>stack.peek()[0]) {
                int[] last = stack.pop();
                if (n<last[1]) return true;
                else {
                    last[1] = n;
                    while (!stack.isEmpty() && n>=stack.peek()[1]) stack.pop();
                    if (!stack.isEmpty() && stack.peek()[0]<n) return true;
                    stack.push(last);
                }
            }
        }
        return false;
    }
}
