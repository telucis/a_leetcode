package complete.bfs.hard;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 相似度为 K 的字符串
 *
    如果可以通过将 A 中的两个小写字母精确地交换位置 K 次得到与 B 相等的字符串，我们称字符串 A 和 B 的相似度为 K（K 为非负整数）。

    给定两个字母异位词 A 和 B ，返回 A 和 B 的相似度 K 的最小值。



    示例 1：

    输入：A = "ab", B = "ba"
    输出：1
    示例 2：

    输入：A = "abc", B = "bca"
    输出：2
    示例 3：

    输入：A = "abac", B = "baca"
    输出：2
    示例 4：

    输入：A = "aabc", B = "abca"
    输出：2


    提示：

    1 <= A.length == B.length <= 20
    A 和 B 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母。

 */
public class k_similar_strings_854 {

    /**
     * bfs
     */
    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(A);
        vis.add(A);
        int res=0;
        while (!q.isEmpty()) {
            res++;
            for (int sz=q.size(); sz>0; sz--) {
                String s = q.poll();
                int i=0;
                while (s.charAt(i)==B.charAt(i)) i++;
                for (int j=i+1; j<s.length(); j++) {
                    if (s.charAt(j)==B.charAt(j) || s.charAt(i)!=B.charAt(j)) continue;
                    String temp = swap(s, i, j);
                    if (temp.equals(B)) return res;
                    if (vis.add(temp)) q.add(temp);
                }
            }
        }
        return res;
    }
    private String swap(String s, int i, int j) {
        char[] ca = s.toCharArray();
        char temp = ca[i];
        ca[i] = ca[j];
        ca[j] = temp;
        return new String(ca);
    }

    /**
     * dfs
     */
    public int kSimilarity2(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        return backtrack(A.toCharArray(), B, map, 0);
    }
    private int backtrack(char[] A, String B, Map<String, Integer> map, int i) {
        String sa=new String(A);
        if (sa.equals(B)) return 0;
        if (map.containsKey(sa)) return map.get(sa);
        int min = Integer.MAX_VALUE;

        while (i<A.length && A[i]==B.charAt(i)) i++;
        for (int j=i+1; j<B.length(); j++) {
            if (A[j]==B.charAt(i)) {
                swap2(A, i, j);
                int next = backtrack(A, B, map, i+1);
                if (next != Integer.MAX_VALUE) {
                    min = Math.min(min, next+1);
                }
                swap2(A, i, j);
            }
        }
        map.put(sa, min);
        return min;
    }
    private void swap2(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
