package complete.dfs.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/26
 *
 * 24 点游戏
 *
    你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。

    示例 1:

    输入: [4, 1, 8, 7]
    输出: True
    解释: (8-4) * (7-1) = 24
    示例 2:

    输入: [1, 2, 1, 2]
    输出: False
    注意:

    除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
    每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
    你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。

 */
public class twentyFour_game_679 {
    boolean res = false;
    final double eps=0.001;
    public boolean judgePoint24(int[] nums) {
        List<Double> arr = new ArrayList<>();
        for (int n : nums) arr.add((double)n);
        helper(arr);
        return res;
    }
    private void helper(List<Double> arr) {
        if (res) return;
        if (arr.size()==1) {
            if (Math.abs(arr.get(0)-24)<eps) res=true;
            return;
        }
        for (int i=0; i<arr.size(); i++) {
            for (int j=0; j<i; j++) {
                List<Double> next = new ArrayList<>();
                Double p1 = arr.get(i), p2=arr.get(j);
                next.addAll(Arrays.asList(p1+p2, p1*p2, p1-p2, p2-p1));
                if (Math.abs(p1)>eps) next.add(p2/p1);
                if (Math.abs(p2)>eps) next.add(p1/p2);
                arr.remove(i);
                arr.remove(j);
                for (Double n: next) {
                    arr.add(n);
                    helper(arr);
                    arr.remove(arr.size()-1);
                }
                arr.add(j, p2);
                arr.add(i, p1);
            }
        }
    }
}
