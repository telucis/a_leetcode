package complete.bit;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 两数相除
 *
    给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

    返回被除数 dividend 除以除数 divisor 得到的商。

    示例 1:

    输入: dividend = 10, divisor = 3
    输出: 3
    示例 2:

    输入: dividend = 7, divisor = -3
    输出: -2
    说明:

    被除数和除数均为 32 位有符号整数。
    除数不为 0。
    假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

 */
public class divide_two_integers_29 {

    /**
     * bit
     *
     */
    public int divide(int  dividend, int divisor) {
        int ans=0, sign=1;
        if ((dividend>0&&divisor<0) || (dividend<0&&divisor>0)) sign=-1;
        long dvd = Math.abs(dividend), dvs = Math.abs(divisor);
        while (dvd>=dvs) {
            long tmp=dvs, m=1;
            while ((tmp<<1) <= dvd) {
                tmp<<=1;
                m<<=1;
            }
            dvd -= tmp;
            ans += m;
        }
        return sign*ans;
    }

    public int divide2(int dividend, int divisor) {
        int sign=1;
        if ((dividend>0&&divisor<0) || (dividend<0&&divisor>0)) {
            sign=-1;
        }
        System.out.println(sign);
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        if (ldivisor==0) return Integer.MAX_VALUE;
        if (ldividend==0 || ldividend<divisor) return 0;
        long ans = helper(ldividend, ldivisor);
        if (ans > Integer.MAX_VALUE) {
            ans = sign==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = sign*ans;
        }
        return (int)ans;
    }
    private long helper(long dividend, long divisor) {
        if (dividend<divisor) return 0;
        long sum = divisor;
        long multiple = 1;
        while (2*sum <= dividend) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + helper(dividend-sum, divisor);
    }
}
