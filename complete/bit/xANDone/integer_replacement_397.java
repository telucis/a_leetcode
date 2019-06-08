package complete.bit.xANDone;

/**
 * @author karl.wy
 * @date 2019/05/31
 *
 * 整数替换
 *
    给定一个正整数 n，你可以做如下操作：

    1. 如果 n 是偶数，则用 n / 2替换 n。
    2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
    n 变为 1 所需的最小替换次数是多少？

    示例 1:

    输入:
    8

    输出:
    3

    解释:
    8 -> 4 -> 2 -> 1
    示例 2:

    输入:
    7

    输出:
    4

    解释:
    7 -> 8 -> 4 -> 2 -> 1
    或
    7 -> 6 -> 3 -> 2 -> 1

 */
public class integer_replacement_397 {

    /**
     * ((N>>>1) & 1)==1
     */
    public int integerReplacement(int n) {
        int c = 0;
        while (n!=1) {
            if ((n & 1) == 0)
                n >>>= 1;
            else if (n == 3 || ((n >>> 1) & 1) == 0)
                --n;
            else
                ++n;
            ++c;
        }
        return c;
    }
}
