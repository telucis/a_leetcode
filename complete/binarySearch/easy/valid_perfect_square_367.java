package complete.binarySearch.easy;

/**
 * @author karl.wy
 * @date 2019/04/16
 *
 * 有效的完全平方数
 *
    给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

    说明：不要使用任何内置的库函数，如  sqrt。

    示例 1：

    输入：16
    输出：True
    示例 2：

    输入：14
    输出：False

 */
public class valid_perfect_square_367 {

    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left+1<right) {
            int mid = (right+left)/2;
            if (mid == num*1.0/mid) {
                return true;
            } else if (mid > num*1.0/mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (left != 0 && left == num*1.0/left) {
            return true;
        }
        if (right == num*1.0/right) {
            return true;
        }
        return false;
    }
}
