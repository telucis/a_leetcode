package complete.dfs.hard;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/26
 *
 * 破解保险箱
 *
    有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。

    你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。

    举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.

    请返回一个能打开保险箱的最短字符串。



    示例1:

    输入: n = 1, k = 2
    输出: "01"
    说明: "10"也可以打开保险箱。


    示例2:

    输入: n = 2, k = 2
    输出: "00110"
    说明: "01100", "10011", "11001" 也能打开保险箱。


    提示：

    n 的范围是 [1, 4]。
    k 的范围是 [1, 10]。m
    k^n 最大可能为 4096。

 */
public class cracking_the_safe_753 {

    public String crackSafe(int n, int k) {
        String strPwd = String.join("", Collections.nCopies(n,"0"));
        StringBuilder sbPwd = new StringBuilder(strPwd);

        Set<String> visitedComb = new HashSet<>();
        visitedComb.add(strPwd);

        int targetNumVisited = (int) Math.pow(k, n);

        crackSafeAfter(sbPwd, visitedComb, targetNumVisited, n,k);

        return sbPwd.toString();
    }
    private boolean crackSafeAfter(StringBuilder pwd, Set<String> visitedComb, int targetNumVisited, int n, int k) {
        if (visitedComb.size()==targetNumVisited) return true;

        String lastDigits = pwd.substring(pwd.length()-n+1);
        for (char ch='0'; ch<'0'+k; ch++) {
            String newComb = lastDigits + ch;
            System.out.println(newComb);
            if (!visitedComb.contains(newComb)) {
                visitedComb.add(newComb);
                pwd.append(ch);
                if (crackSafeAfter(pwd, visitedComb, targetNumVisited, n, k)) {
                    return true;
                }
                visitedComb.remove(newComb);
                pwd.deleteCharAt(pwd.length()-1);
            }
        }
        return false;
    }
}
