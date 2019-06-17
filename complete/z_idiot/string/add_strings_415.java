package complete.z_idiot.string;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 字符串相加
 *
    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

    注意：

    num1 和num2 的长度都小于 5100.
    num1 和num2 都只包含数字 0-9.
    num1 和num2 都不包含任何前导零。
    你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

 */
public class add_strings_415 {

    public String addStrings(String num1, String num2) {
        int m = num1.length()-1;
        int n = num2.length()-1;
        StringBuilder res = new StringBuilder();
        int carray = 0;
        while (m >= 0 || n >= 0) {
            if (m >= 0) {
                carray += num1.charAt(m) - '0';
            }
            if (n >= 0) {
                carray += num2.charAt(n) - '0';
            }
            res.append(carray%10);
            carray = carray/10;
            m--;
            n--;
        }
        if (carray != 0) {
            res.append(carray);
        }
        return res.reverse().toString();
    }
}
