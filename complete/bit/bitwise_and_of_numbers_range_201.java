package complete.bit;

/**
 * @author karl.wy
 * @date 2019/05/31
 *
 * 数字范围按位与
 *
    给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

    示例 1:

    输入: [5,7]
    输出: 4
    示例 2:

    输入: [0,1]
    输出: 0

 */
public class bitwise_and_of_numbers_range_201 {

    /**
     * bit
     * (m & ~((1<<i)-1L))+(1<<i) > n
     */
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        for (int i=0; 1<<i<=m; i++) {
            if ((m>>i & 1) == 1) {
                if ((m & ~((1<<i)-1L))+(1<<i)>n)
                    res += 1<<i;
            }
        }
        return res;
    }
}
