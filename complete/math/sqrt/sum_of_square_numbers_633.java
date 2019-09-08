package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 平方数之和
 *
    给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。

    示例1:

    输入: 5
    输出: True
    解释: 1 * 1 + 2 * 2 = 5


    示例2:

    输入: 3
    输出: False

 */
public class sum_of_square_numbers_633 {

    public boolean judgeSquareSum(int c) {
        int b = (int)Math.sqrt(c), a = 0;
        while (a < b) {
            int sum = a*a + b*b;
            if (sum==c) {
                return true;
            } else if (sum > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }
}
