package easy.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/16
 *
 * x的平方根
 *

    实现 int sqrt(int x) 函数。

    计算并返回 x 的平方根，其中 x 是非负整数。

    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

    示例 1:

    输入: 4
    输出: 2
    示例 2:

    输入: 8
    输出: 2
    说明: 8 的平方根是 2.82842...,
    由于返回类型是整数，小数部分将被舍去。
 */
public class sqrtx_69 {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 0, right = x;
        while (left+1<right) {
            int mid = (left+right)/2;
            if (mid == x/mid) {
                return mid;
            } else if (mid >= x/mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right <= x/right) {
            return right;
        } else  {
            return left;
        }
    }
}
