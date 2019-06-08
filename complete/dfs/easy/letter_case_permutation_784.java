package complete.dfs.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 字母大小写全排列
 *
    给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。

    示例:
    输入: S = "a1b2"
    输出: ["a1b2", "a1B2", "A1b2", "A1B2"]

    输入: S = "3z4"
    输出: ["3z4", "3Z4"]

    输入: S = "12345"
    输出: ["12345"]
    注意：

    S 的长度不超过12。
    S 仅由数字和字母组成。

 */
public class letter_case_permutation_784 {

    public List<String> letterCasePermutation(String S) {
        return helper(S, 0);
    }
    private List<String> helper(String S, int start) {
        List<String> res = new ArrayList<>();
        int end = start;
        while (end<S.length() && !isLetter(S.charAt(end))) {
            end++;
        }
        String tmp = S.substring(start, end);
        if (end == S.length()) {
            return new ArrayList<String>(){{add(tmp);}};
        }
        List<String> childs = helper(S, end+1);
        for (String child : childs) {
            res.add(tmp + S.substring(end, end+1).toLowerCase() + child);
            res.add(tmp + S.substring(end, end+1).toUpperCase() + child);
        }
        return res;
    }
    private boolean isLetter(char c) {
        if ((c>='a' &&c<='z') || (c>='A' && c<='Z')) {
            return true;
        }
        return false;
    }
}
