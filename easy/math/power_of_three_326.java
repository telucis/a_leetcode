package easy.math;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 3的幂
 *
    给定一个整数，写一个函数来判断它是否是 3 的幂次方。

    示例 1:

    输入: 27
    输出: true
    示例 2:

    输入: 0
    输出: false
    示例 3:

    输入: 9
    输出: true
    示例 4:

    输入: 45
    输出: false
    进阶：
    你能不使用循环或者递归来完成本题吗？


 */
public class power_of_three_326 {

    public boolean isPowerOfThree(int n) {
        if (n<=0) {
            return false;
        }
        while (n!=1) {
            if (n%3 != 0) {
                return false;
            }
            n/=3;
        }
        return true;
    }
}
