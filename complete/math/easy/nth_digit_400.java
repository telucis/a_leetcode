package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 第N个数字
 *
    在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。

    注意:
    n 是正数且在32为整形范围内 ( n < 2^31)。

    示例 1:

    输入:
    3

    输出:
    3
    示例 2:

    输入:
    11

    输出:
    0

    说明:
    第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。

 */
public class nth_digit_400 {

    /**
        1. find the length of the number where the nth digit is from
        2. find the actual number where the nth digit is from
        3. find the nth digit and return
     */
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n>len*count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }
        start += (n-1)/len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n-1)%len));
    }

    //1234567891011121314151617181920
    //1 - [1,9]             9 * 1
    //2 - [10,99]          90 * 2
    //3 - [100,999]       900 * 3
    //4 - [1000,9999]    9000 * 4
    //.........2^31 = 2147483648
    public int findNthDigit2(int n) {
        if (n<10) {
            return n;
        }
        long length = 0, cnt = 9, i=1;
        for (; length + cnt*i < n; i++) {
            length += cnt * i;
            cnt *= 10;
        }
        long num = (long)Math.pow(10, i-1)-1 + (n-length+1)/i;
        long index = (n-length-1)%1;
        for (int k=0; k<i-index-1; k++) {
            num /= 10;
        }
        return (int)num%10;
    }
}
