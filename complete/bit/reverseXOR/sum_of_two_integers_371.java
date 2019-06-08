package complete.bit.reverseXOR;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 两整数之和
 *
    不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。

    示例 1:

    输入: a = 1, b = 2
    输出: 3
    示例 2:

    输入: a = -2, b = 3
    输出: 1

 */
public class sum_of_two_integers_371 {

    /**
     * bit
     * a^b : 不进位加法
     * a&b<<1 : 进位数
     */
    public int getSum(int a, int b) {
        int sum, carry;
        sum = a ^ b;
        carry = (a & b) << 1;
        if (carry != 0) {
            return getSum(sum, carry);
        }
        return sum;
    }
}
