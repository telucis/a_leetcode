package complete.bit.xANDnx;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 4的幂
 *
    给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。

    示例 1:

    输入: 16
    输出: true
    示例 2:

    输入: 5
    输出: false
    进阶：
    你能不使用循环或者递归来完成本题吗？


 */
public class power_of_four_342 {

    /**
     * n&=-n
     */
    public boolean isPowerOfFour(int num) {
        if (num<4) {
            return false;
        }
        while (num > 1) {
            if (num % 4 != 0) {
                return false;
            }
            num /= 4;
        }
        return true;
    }
}
