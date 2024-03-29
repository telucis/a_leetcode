package complete.stack.recursion;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 字符串解码
 *
    给定一个经过编码的字符串，返回它解码后的字符串。

    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

    示例:

    s = "3[a]2[bc]", 返回 "aaabcbc".
    s = "3[a2[c]]", 返回 "accaccacc".
    s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

 */
public class decode_string_394 {

    public String decodeString(String s) {
        String ans = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx=0;
        while (idx<s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count=0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10*count+(s.charAt(idx)-'0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx)=='[') {
                resStack.push(ans);
                ans="";
                idx++;
            } else if (s.charAt(idx)==']') {
                StringBuilder tmp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i=0; i<repeatTimes; i++) {
                    tmp.append(ans);
                }
                ans=tmp.toString();
                idx++;
            } else {
                ans+=s.charAt(idx++);
            }
        }
        return ans;
    }
}
