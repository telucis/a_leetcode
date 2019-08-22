package complete.array.mid.chunk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 划分字母区间
 *
    字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

    示例 1:

    输入: S = "ababcbacadefegdehijhklij"
    输出: [9,7,8]
    解释:
    划分结果为 "ababcbaca", "defegde", "hijhklij"。
    每个字母最多出现在一个片段中。
    像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
    注意:

    S的长度在[1, 500]之间。
    S只包含小写字母'a'到'z'。

 */
public class partition_labels_763 {

    public List<Integer> partitionLabels(String S) {
        if (S==null || S.length()==0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];
        for (int i=0; i<S.length(); i++) {
            map[S.charAt(i)-'a'] = i;
        }
        int last=0, start=0;
        for (int i=0; i<S.length(); i++) {
            last = Math.max(last, map[S.charAt(i)-'a']);
            if (last==i) {
                list.add(last-start+1);
                start=i+1;
            }
        }
        return list;
    }
}
