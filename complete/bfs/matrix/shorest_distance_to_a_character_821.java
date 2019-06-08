package complete.bfs.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * 字符的最短距离
 *
    给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。

    示例 1:

    输入: S = "loveleetcode", C = 'e'
    输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
    说明:

    字符串 S 的长度范围为 [1, 10000]。
    C 是一个单字符，且保证是字符串 S 里的字符。
    S 和 C 中的所有字母均为小写字母。

 */
public class shorest_distance_to_a_character_821 {

    public int[] shortestToChar(String S, char C) {
        char[] list = S.toCharArray();
        int[] res = new int[list.length];
        Arrays.fill(res, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<list.length; i++) {
            if (list[i] == C) {
                queue.offer(i);
                res[i] = 0;
            }
        }
        int[] dx = new int[]{-1, 1};
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int len = queue.size();
            for (int i=0; i<len; i++) {
                int index = queue.poll();
                for (int j=0; j<2; j++) {
                    int newIndex = index+dx[j];
                    if (newIndex>=0 && newIndex<res.length && res[newIndex]==-1) {
                        res[newIndex] = cnt;
                        queue.offer(newIndex);
                    }
                }
            }
        }
        return res;
    }
}
