package complete.stack.parentheses;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 括号的分数
 *
    给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

    () 得 1 分。
    AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
    (A) 得 2 * A 分，其中 A 是平衡括号字符串。


    示例 1：

    输入： "()"
    输出： 1
    示例 2：

    输入： "(())"
    输出： 2
    示例 3：

    输入： "()()"
    输出： 2
    示例 4：

    输入： "(()(()))"
    输出： 6


    提示：

    S 是平衡括号字符串，且只含有 ( 和 ) 。
    2 <= S.length <= 50

 */
public class score_of_parentheses_856 {

    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur*2, 1);
            }
        }
        return cur;
    }
}
