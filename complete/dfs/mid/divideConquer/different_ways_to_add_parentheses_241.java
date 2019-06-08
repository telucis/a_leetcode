package complete.dfs.mid.divideConquer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 为运算表达式设计优先级
 *
    给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。

    示例 1:

    输入: "2-1-1"
    输出: [0, 2]
    解释:
    ((2-1)-1) = 0
    (2-(1-1)) = 2
    示例 2:

    输入: "2*3-4*5"
    输出: [-34, -14, -10, -10, 10]
    解释:
    (2*(3-(4*5))) = -34
    ((2*3)-(4*5)) = -14
    ((2*(3-4))*5) = -10
    (2*((3-4)*5)) = -10
    (((2*3)-4)*5) = 10

 */
public class different_ways_to_add_parentheses_241 {

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new LinkedList<>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i)=='-' ||
                input.charAt(i)=='+' ||
                input.charAt(i)=='*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 : part1Ret) {
                    for (Integer p2 : part2Ret) {
                        int c=0;
                        switch (input.charAt(i)) {
                            case '+': c=p1+p2;
                                break;
                            case '-': c=p1-p2;
                                break;
                            case '*': c=p1*p2;
                                break;
                        }
                        ans.add(c);
                    }
                }
            }
        }
        if (ans.size()==0) ans.add(Integer.valueOf(input));
        return ans;
    }
}
