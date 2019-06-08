package complete.bit.xANDnx;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 2的幂
 *
    给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

    示例 1:

    输入: 1
    输出: true
    解释: 20 = 1
    示例 2:

    输入: 16
    输出: true
    解释: 24 = 16
    示例 3:

    输入: 218
    输出: false

 */
public class power_of_two_231 {

    /**
     * n&-n : 等于n的二进制标识里最右边一个1
     */
    public boolean isPowerOfTwo(int n) {
        return n>0 && (n&-n)==n;
    }


    public boolean isPowerOfTwo2(int n) {
        return n>0 && (1<<30)%n==0;
    }

    public boolean isPowerOfTwo3(int n) {
        if (n<1) {
            return false;
        }
        while (n > 1) {
            if (n%2 != 0) {
                return false;
            }
            n /= 2;
        }
        return true;
    }
}
