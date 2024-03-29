package complete.dfs.mid.backtracking;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 金字塔转换矩阵
 *
    现在，我们用一些方块来堆砌一个金字塔。 每个方块用仅包含一个字母的字符串表示，例如 “Z”。

    使用三元组表示金字塔的堆砌规则如下：

    (A, B, C) 表示，“C”为顶层方块，方块“A”、“B”分别作为方块“C”下一层的的左、右子块。当且仅当(A, B, C)是被允许的三元组，我们才可以将其堆砌上。

    初始时，给定金字塔的基层 bottom，用一个字符串表示。一个允许的三元组列表 allowed，每个三元组用一个长度为 3 的字符串表示。

    如果可以由基层一直堆到塔尖返回true，否则返回false。

    示例 1:

    输入: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
    输出: true
    解析:
    可以堆砌成这样的金字塔:
        A
       / \
      D   E
     / \ / \
    X   Y   Z

    因为符合('X', 'Y', 'D'), ('Y', 'Z', 'E') 和 ('D', 'E', 'A') 三种规则。
    示例 2:

    输入: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
    输出: false
    解析:
    无法一直堆到塔尖。
    注意, 允许存在三元组(A, B, C)和 (A, B, D) ，其中 C != D.
    注意：

    bottom 的长度范围在 [2, 8]。
    allowed 的长度范围在[0, 200]。
    方块的标记字母范围为{'A', 'B', 'C', 'D', 'E', 'F', 'G'}。

 */
public class pyramid_transition_matrix_756 {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String str : allowed) {
            map.putIfAbsent(str.substring(0, 2), new HashSet<>());
            map.get(str.substring(0, 2)).add(str.substring(2));
        }
        return helper(bottom, map);
    }
    private boolean helper(String bottom, Map<String, Set<String>> map) {
        if (bottom.length()==1) return true;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<bottom.length()-1; i++) {
            String key = bottom.substring(i, i+2);
            if (!map.containsKey(key)) return false;
        }
        List<String> ls = new ArrayList<>();
        getList(bottom, 0, new StringBuilder(), ls, map);
        for (String b : ls) {
            if (helper(b, map)) return true;
        }
        return false;
    }
    private void getList(String bottom, int idx, StringBuilder sb, List<String> ls, Map<String, Set<String>> map) {
        if (idx == bottom.length()-1) {
            ls.add(sb.toString());
            return;
        }
        for (String s : map.get(bottom.substring(idx, idx+2))) {
            sb.append(s);
            getList(bottom, idx+1, sb, ls, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
