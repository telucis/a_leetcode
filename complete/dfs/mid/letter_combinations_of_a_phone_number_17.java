package complete.dfs.mid;

import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 电话号码的字母组合
 *
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



    示例:

    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    说明:
    尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。


 */
public class letter_combinations_of_a_phone_number_17 {
    private static final String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> ret = new LinkedList<>();
        if (digits.length()==0) {
            return ret;
        }
        combination("", digits, 0, ret);
        return ret;
    }

    public void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = keys[digits.charAt(offset) - '0'];
        for (int i=0; i<letters.length(); i++) {
            combination(prefix+letters.charAt(i), digits, offset+1, ret);
        }
    }
}
