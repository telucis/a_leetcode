package complete.dfs.mid.traversal;

import java.math.BigInteger;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 累加数
 *
    累加数是一个字符串，组成它的数字可以形成累加序列。

    一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

    给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。

    说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

    示例 1:

    输入: "112358"
    输出: true
    解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
    示例 2:

    输入: "199100199"
    输出: true
    解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
    进阶:
    你如何处理一个溢出的过大的整数输入?


 */
public class additive_number_306 {

    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i=1; i<=n/2; i++) {
            if (num.charAt(0)=='0' && i>1) return false;
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j=1; Math.max(j, i)<=n-i-j; j++) {
                if (num.charAt(i)=='0' && j>1) break;
                BigInteger x2 = new BigInteger(num.substring(i, i+j));
                if (isValid(x1, x2, j+i, num)) return true;
            }
        }
        return false;
    }
    private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
        if (start==num.length()) return true;
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, start+sum.length(), num);
    }
}
