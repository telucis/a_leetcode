package medium.array;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 子串能表示从 1 到 N 数字的二进制串
 *
    给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。



    示例 1：

    输入：S = "0110", N = 3
    输出：true
    示例 2：

    输入：S = "0110", N = 4
    输出：false


    提示：

    1 <= S.length <= 1000
    1 <= N <= 10^9

 */
public class binary_string_with_substrings_representing_1_to_n_1016 {

    /**
     * O(S*31*31)
     */
    public boolean queryString(String S, int N) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<S.length(); i++) {
            for (int j=Math.max(i-30, 0); j<=i; j++) {
                int v = 0;
                for (int bit=j; bit<=i; bit++) {
                    v = v*2 + (S.charAt(bit)-'0');
                }
                if (v>0 && v<=N) {
                    set.add(v);
                }
            }
        }
        return set.size() == N;
    }

    public boolean queryString2(String S, int N) {
        boolean[] seen = new boolean[N];
        int X = 0;
        for (int i=0; i<S.length(); i++) {
            if (S.charAt(i)=='0') {
                continue;
            }
            for (int j=i, num=0; num<=N && j<S.length(); ++j) {
                num = (num<<1) + S.charAt(j) -'0';
                if (num>0 && num<=N && !seen[num-1]) {
                    ++X;
                    seen[num-1] = true;
                }
            }
        }
        return N == X;
    }
}
