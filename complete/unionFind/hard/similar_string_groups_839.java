package complete.unionFind.hard;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 相似字符串组
 *
    如果我们交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。

    例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

    总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

    我们给出了一个不包含重复的字符串列表 A。列表中的每个字符串都是 A 中其它所有字符串的一个字母异位词。请问 A 中有多少个相似字符串组？

    示例：

    输入：["tars","rats","arts","star"]
    输出：2
    提示：

    A.length <= 2000
    A[i].length <= 1000
    A.length * A[i].length <= 20000
    A 中的所有单词都只包含小写字母。
    A 中的所有单词都具有相同的长度，且是彼此的字母异位词。
    此问题的判断限制时间已经延长。
    备注：

    字母异位词[anagram]，一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。


 */
public class similar_string_groups_839 {
    HashMap<String, String> map = new HashMap<>();
    int count;
    public int numSimilarGroups(String[] A) {
        for (String a : A) {
            map.put(a, a);
        }
        count=map.size();
        for (String a : A) {
            for (String b : A) {
                if (!a.equals(b) && similar(a, b)) {
                    union(a, b);
                }
            }
        }
        return count;
    }
    private boolean similar(String a, String b) {
        int l=0, r=a.length()-1;
        while (a.charAt(l)==b.charAt(l)) l++;
        while (a.charAt(r)==b.charAt(r)) r--;
        for (int i=0; i<a.length(); i++) {
            if (i==l) {
                if (a.charAt(r)!=b.charAt(i)) return false;
            } else if (i==r) {
                if (a.charAt(l)!=b.charAt(i)) return false;
            } else {
                if (a.charAt(i)!=b.charAt(i)) return false;
            }
        }
        return true;
    }
    private void union(String a, String b) {
        if (find(a).equals(find(b))) return;
        map.put(find(a), find(b));
        count--;
    }
    private String find(String a) {
        if (!map.get(a).equals(a)) {
            map.put(a, find(map.get(a)));
        }
        return map.get(a);
    }
}
