package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 七进制数
 *
    给定一个整数，将其转化为7进制，并以字符串形式输出。

    示例 1:

    输入: 100
    输出: "202"
    示例 2:

    输入: -7
    输出: "-10"
    注意: 输入范围是 [-1e7, 1e7] 。


 */
public class base_7_504 {

    public String convertToBase7(int num) {
        if (num==0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean n = false;
        if (num<0) {
            n = true;
            num = -num;
        }
        while (num > 0) {
            sb.append(num%7);
            num/=7;
        }
        sb = sb.reverse();
        if (n) {
            return "-"+sb.toString();
        }
        return sb.toString();
    }
}
