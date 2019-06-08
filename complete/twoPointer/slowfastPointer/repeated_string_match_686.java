package complete.twoPointer.slowfastPointer;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 重复叠加字符串匹配
 *
    给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。

    举个例子，A = "abcd"，B = "cdabcdab"。

    答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。

    注意:

    A 与 B 字符串的长度在1和10000区间范围内。


 */
public class repeated_string_match_686 {

    public int repeatedStringMatch(String A, String B) {
        int res = 1;
        String tmp = A;
        while (tmp.length() < B.length()) {
            tmp += A;
            res++;
        }
        if (isSubStr(tmp, B)) {
            return res;
        } else {
            tmp += A;
            res++;
            if (isSubStr(tmp, B)) {
                return res;
            }
        }
        return -1;
    }
    private boolean isSubStr(String A, String B) {
        int startIndex = -1;
        for (int i=0; i<A.length(); i++) {
            if (startIndex == -1 && B.charAt(0) == A.charAt(i)) {
                startIndex = i;
            }
            if (startIndex != -1) {
                if (B.charAt(i - startIndex) != A.charAt(i)) {
                    i = startIndex;
                    startIndex = -1;
                    continue;
                }
                if (i-startIndex == B.length()-1) {
                    return true;
                }
            }
        }
        return false;
    }
}
