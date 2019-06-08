package complete.math.number;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 丑数
 *
    编写一个程序判断给定的数是否为丑数。

    丑数就是只包含质因数 2, 3, 5 的正整数。

    示例 1:

    输入: 6
    输出: true
    解释: 6 = 2 × 3
    示例 2:

    输入: 8
    输出: true
    解释: 8 = 2 × 2 × 2
    示例 3:

    输入: 14
    输出: false
    解释: 14 不是丑数，因为它包含了另外一个质因数 7。
    说明：

    1 是丑数。
    输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。

 */
public class ugly_number_263 {

    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        if (num==1) {
            return true;
        }
        boolean flag = true;
        while (num != 1 && flag) {
            flag = false;
            if (num%2==0) {
                flag = true;
                num/=2;
            }
            if (num%3==0) {
                flag = true;
                num/=3;
            }
            if (num%5==0) {
                flag = true;
                num/=5;
            }
        }
        return flag;
    }
}
