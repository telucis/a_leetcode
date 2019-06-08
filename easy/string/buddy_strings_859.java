package easy.string;

import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 亲密字符串
 *
    给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。

    示例 1：

    输入： A = "ab", B = "ba"
    输出： true
    示例 2：

    输入： A = "ab", B = "ab"
    输出： false
    示例 3:

    输入： A = "aa", B = "aa"
    输出： true
    示例 4：

    输入： A = "aaaaaaabc", B = "aaaaaaacb"
    输出： true
    示例 5：

    输入： A = "", B = "aa"
    输出： false


    提示：

    0 <= A.length <= 20000
    0 <= B.length <= 20000
    A 和 B 仅由小写字母构成。

 */
public class buddy_strings_859 {

    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            HashSet<Character> set = new HashSet<>();
            for (int i=0; i<A.length(); i++) {
                set.add(A.charAt(i));
            }
            if (set.size() < A.length()) {
                return true;
            }
            return false;
        }
        int diffCount = 0;
        char a1='a', a2='a', b1='a', b2='a';
        for (int i=0; i<A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (diffCount == 0) {
                    a1 = A.charAt(i);
                    b1 = B.charAt(i);
                }
                if (diffCount == 1) {
                    a2 = A.charAt(i);
                    b2 = B.charAt(i);
                }
                diffCount++;
                if (diffCount >2) {
                    return false;
                }
            }
        }
        if (diffCount != 2) {
            return false;
        }
        if (a1 == b2 && a2 == b1) {
            return true;
        } else {
            return false;
        }
    }
}
