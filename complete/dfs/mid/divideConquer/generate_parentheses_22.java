package complete.dfs.mid.divideConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 括号生成
 *
    给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

    例如，给出 n = 3，生成结果为：

    [
    "((()))",
    "(()())",
    "(())()",
    "()(())",
    "()()()"
    ]

 */
public class generate_parentheses_22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res, "", n, 0, 0);
        return res;
    }
    private void  generate(List<String> res, String ans, int n, int count1, int count2) {
        if (count1==n && count2==n) {
            res.add(ans);
            return;
        }
        if (count1>n || count2>n) {
            return;
        }
        if (count1>=count2) {
            generate(res, ans+"(", n, count1+1, count2);
            generate(res, ans+")", n, count1, count2+1);
        }
    }
}
