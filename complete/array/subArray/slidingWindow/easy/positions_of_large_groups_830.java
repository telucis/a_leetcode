package complete.array.subArray.slidingWindow.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 较大分组的位置
 *
    在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。

    例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。

    我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。

    最终结果按照字典顺序输出。

    示例 1:

    输入: "abbxxxxzzy"
    输出: [[3,6]]
    解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
    示例 2:

    输入: "abc"
    输出: []
    解释: "a","b" 和 "c" 均不是符合要求的较大分组。
    示例 3:

    输入: "abcdddeeeeaabbbcd"
    输出: [[3,5],[6,9],[12,14]]
    说明:  1 <= S.length <= 1000


 */
public class positions_of_large_groups_830 {

    public List<List<Integer>> largeGroupPositions(String S) {
        int count = 1;
        List<List<Integer>> res = new ArrayList<>();
        for (int i=1; i<S.length(); i++) {
            if (S.charAt(i) == S.charAt(i-1)) {
                count++;
            } else {
                if (count >= 3) {
                    System.out.println(count);
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i-count);
                    tmp.add(i-1);
                    res.add(tmp);
                }
                count = 1;
            }
        }
        if (count>=3) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(S.length()-count);
            tmp.add(S.length()-1);
            res.add(tmp);
        }
        return res;
    }
}
