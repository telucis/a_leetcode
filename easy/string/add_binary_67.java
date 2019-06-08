package easy.string;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 二进制求和
 *
    给定两个二进制字符串，返回他们的和（用二进制表示）。

    输入为非空字符串且只包含数字 1 和 0。

    示例 1:

    输入: a = "11", b = "1"
    输出: "100"
    示例 2:

    输入: a = "1010", b = "1011"
    输出: "10101"

 */
public class add_binary_67 {

    public String addBinary(String a, String b) {
        boolean pre = false;
        int m = a.length()-1;
        int n = b.length()-1;
        String res = "";
        while (m>=0 || n>=0) {
            String tmp = "";
            if (m >= 0 && n >= 0) {
                if (a.charAt(m)=='1' && b.charAt(n)=='1') {
                    if (pre) {
                        res = "1" + res;
                    } else {
                        res = "0" + res;
                    }
                    pre = true;
                } else if (a.charAt(m)== '1' || b.charAt(n)=='1') {
                    if (pre) {
                        res = "0" + res;
                        pre = true;
                    } else {
                        res = "1" + res;
                        pre = false;
                    }
                } else {
                    if (pre) {
                        res = "1" + res;
                    } else {
                        res = "0" + res;
                    }
                    pre = false;
                }
            } else if (m >= 0) {
                if (pre) {
                    if (a.charAt(m)=='1') {
                        res = "0" + res;
                    } else {
                        pre = false;
                        res = "1" + res;
                    }
                } else {
                    res = String.valueOf(a.charAt(m)) + res;
                }
            } else if (n >= 0) {
                if (pre) {
                    if (b.charAt(n)=='1') {
                        res = "0" + res;
                    } else {
                        pre = false;
                        res = "1" + res;
                    }
                } else {
                    res = String.valueOf(b.charAt(n)) + res;
                }
            }
            m--;
            n--;
        }
        if (pre) {
            res = "1" + res;
        }
        return res;
    }
}
