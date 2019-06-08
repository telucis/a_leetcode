package complete.bfs.hard;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 删除无效的括号
 *
    删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。

    说明: 输入可能包含了除 ( 和 ) 以外的字符。

    示例 1:

    输入: "()())()"
    输出: ["()()()", "(())()"]
    示例 2:

    输入: "(a)())()"
    输出: ["(a)()()", "(a())()"]
    示例 3:

    输入: ")("
    输出: [""]

 */
public class remove_invalid_parentheses_301 {

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s==null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);
        boolean found=false;

        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                res.add(s);
                found=true;
            }
            if (found) continue;
            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i)!='(' && s.charAt(i)!=')')continue;
                String t = s.substring(0,i)+s.substring(i+1);
                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }
    private boolean isValid(String s) {
        int count=0;
        for (int i=0; i<s.length(); i++) {
            char c=s.charAt(i);
            if (c=='(') count++;
            else if (c==')'){
                if (count==0) return false;
                count--;
            }
        }
        return count==0;
    }
}
