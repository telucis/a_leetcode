package medium.stack;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 基本计算器 II
 *
    实现一个基本的计算器来计算一个简单的字符串表达式的值。

    字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

    示例 1:

    输入: "3+2*2"
    输出: 7
    示例 2:

    输入: " 3/2 "
    输出: 1
    示例 3:

    输入: " 3+5 / 2 "
    输出: 5
    说明：

    你可以假设所给定的表达式都是有效的。
    请不要使用内置的库函数 eval。

 */
public class basic_calculator_ii_227 {

    public int calculate(String s) {
        int len;
        if (s==null || (len=s.length())==0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign =  '+';
        for (int i=0; i<len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num*10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i))) && ' '!=s.charAt(i) || i==len-1) {
                if (sign=='-') {
                    stack.push(-num);
                } else if (sign=='+') {
                    stack.push(num);
                } else if (sign=='*') {
                    stack.push(stack.pop()*num);
                } else if (sign=='/') {
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num=0;
            }
        }
        int re = 0;
        for (int i: stack) {
            re += i;
        }
        return re;
    }
}
