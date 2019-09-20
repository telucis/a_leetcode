/**
 * 32. 最长有效括号
 *
     给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

    示例 1:

    输入: "(()"
    输出: 2
    解释: 最长有效括号子串为 "()"
    示例 2:

    输入: ")()())"
    输出: 4
    解释: 最长有效括号子串为 "()()"
 */

class longest_valid_parentheses_32 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        boolean[] list = new boolean[s.length()];
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)=='(') {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                list[stack.pop()]=true;
                list[i]=true;
            }
        }
        int cur=0, max=0;
        for (int i=0; i<list.length; i++) {
            if (list[i]) cur+=1;
            else {
                max = Math.max(max, cur);
                cur=0;
            }
        }
        return Math.max(max, cur);
    }

    public int longestValidParentheses2(String s) {
        char[] chars = s.toCharArrays();
        return Math.max(calc(chars, 0, 1, s.length(), '('),
                calc(chars, s.length()-1, -1, -1, ')')
            );
    }
    private int calc(char[] chars, int i, int flag, int end, char cTem) {
        int max=0, sum=0, currLen=0, validLen=0;
        for (; i!=end; i+=flag) {
            sum += (chars[i]==cTem ? 1 : -1);
            currLen++;
            if (sum < 0) {
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            } else if (sum == 0) {
                validLen = currLen;
            }
        }
        return max>validLen ? max : validLen;
    }
}