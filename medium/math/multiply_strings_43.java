package medium.math;

import java.util.Collections;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 字符串相乘
 *
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

    示例 1:

    输入: num1 = "2", num2 = "3"
    输出: "6"
    示例 2:

    输入: num1 = "123", num2 = "456"
    输出: "56088"
    说明：

    num1 和 num2 的长度小于110。
    num1 和 num2 只包含数字 0-9。
    num1 和 num2 均不以零开头，除非是数字 0 本身。
    不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

 */
public class multiply_strings_43 {
    /**
     num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
     例: 123 * 45,  123的第1位 2 和45的第0位 4 乘积 08 存放在结果的第[1, 2]位中
     index:    0 1 2 3 4

                   1 2 3
               *     4 5
               ---------
                     1 5
                   1 0
                 0 5
               ---------
                 0 6 1 5
                   1 2
                 0 8
               0 4
               ---------
               0 5 5 3 5
     这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中
     **/
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m+n];
        for (int i=n-1; i>=0; --i) {
            for (int j=m-1; j>=0; --j) {
                int bitmul = (num2.charAt(i)-'0') * (num1.charAt(j)-'0');
                bitmul += pos[i+j+1];
                pos[i+j+1] = bitmul%10;
                pos[i+j] += bitmul/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i=0;
        while (pos[i]==0) {
            i++;
        }
        for (int k : pos) {
            System.out.println(k);
        }
        for (; i<m+n; i++) {
            sb.append(String.valueOf(pos[i]));
        }
        return sb.toString();
    }
}
