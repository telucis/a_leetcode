package complete.heap;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 重构字符串
 *
    给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

    若可行，输出任意可行的结果。若不可行，返回空字符串。

    示例 1:

    输入: S = "aab"
    输出: "aba"
    示例 2:

    输入: S = "aaab"
    输出: ""
    注意:

    S 只包含小写字母并且长度在[1, 500]区间内。

 */
public class reorganize_string_767 {

    public String reorganizeString(String S) {
        // create map of each char to its count
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0; i<S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0)+1);
            if (map.get(S.charAt(i)) > (S.length()+1)/2) {
                return "";
            }
        }
        // greedy : fetch char of max count as next char in the result
        // use priorityQueue to store pairs of (char, count) and sort by count DESC
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->b[1]-a[1]);
        for (char c : map.keySet()) {
            pq.add(new int[]{c, map.get(c)});
        }
        // build the result
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length()==0 || first[0]!=sb.charAt(sb.length()-1)) {
                sb.append((char)first[0]);
                if (--first[1]>0) {
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char)second[0]);
                if (--second[1]>0) {
                    pq.add(second);
                }
                pq.add(first);
            }
        }
        return sb.toString();
    }
}
