package complete.stack.hard;

import java.util.Stack;

/**
 * Created by telucis on 2019/6/12.
 *
 * 基本计算器
 *
     实现一个基本的计算器来计算一个简单的字符串表达式的值。

     字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。

     示例 1:

     输入: "1 + 1"
     输出: 2
     示例 2:

     输入: " 2-1 + 2 "
     输出: 3
     示例 3:

     输入: "(1+(4+5+2)-3)+(6+8)"
     输出: 23
     说明：

     你可以假设所给定的表达式都是有效的。
     请不要使用内置的库函数 eval。

 */
public class basic_calculator_224 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            }else if(c == '+'){
                result += sign * number;
                number = 0;
                sign = 1;
            }else if(c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if(number != 0) result += sign * number;
        return result;
    }
}
