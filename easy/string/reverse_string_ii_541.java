package easy.string;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 反转字符串 ii
 *
    给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

    示例:

    输入: s = "abcdefg", k = 2
    输出: "bacdfeg"
    要求:

    该字符串只包含小写的英文字母。
    给定字符串的长度和 k 在[1, 10000]范围内。
 */
public class reverse_string_ii_541 {

    public String reverseStr(String s, int k) {
        String res = "";
        int n = s.length();
        StringBuilder tmp = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            if ((i/k)%2 == 0) {
                if (i%k==0 && tmp.length() != 0) {
                    res += tmp.toString();
                    tmp.delete(0, k);
                }
                tmp.append(s.charAt(i));
            } else {
                if (i%k==0) {
                    res += tmp.reverse().toString();
                    tmp.delete(0, k);
                }
                tmp.append(s.charAt(i));
            }
        }
        if (tmp.length() != 0) {
            if (((n-1)/k)%2 == 0) {
                res += tmp.reverse().toString();
            } else {
                res += tmp.toString();
            }
        }
        return res;
    }
}
