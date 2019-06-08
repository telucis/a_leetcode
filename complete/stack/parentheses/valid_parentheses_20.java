package complete.stack.parentheses;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 有效的括号
 *
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

    有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    注意空字符串可被认为是有效字符串。

    示例 1:

    输入: "()"
    输出: true
    示例 2:

    输入: "()[]{}"
    输出: true
    示例 3:

    输入: "(]"
    输出: false
    示例 4:

    输入: "([)]"
    输出: false
    示例 5:

    输入: "{[]}"
    输出: true

 */
public class valid_parentheses_20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else if ((stack.peek().equals('{') && s.charAt(i) == '}') ||
                (stack.peek().equals('(') && s.charAt(i) == ')') ||
                (stack.peek().equals('[') && s.charAt(i) == ']')) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
