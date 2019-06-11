package complete.stack.monotone;

import java.util.Stack;

/**
 * Created by telucis on 2019/5/12.
 *
 * 每日温度
 *
     根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。

     例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

     提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。


 */
public class daily_temperatures_739 {

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[T.length];
        for (int i=0; i<T.length; i++) {
            while (!stack.isEmpty() && stack.peek()<T[i]) {
                int idx = stack.pop();
                ret[idx] = i-idx;
            }
            stack.push(T[i]);
        }
        return ret;
    }
}
