package easy.zero;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 第N个数字
 *
    在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。

    注意:
    n 是正数且在32为整形范围内 ( n < 231)。

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

    //1234567891011121314151617181920
    //1 - [1,9]             9
    //2 - [10,99]          90
    //3 - [100,999]       900
    //4 - [1000,9999]    9000
    //.........2^31 = 2147483648
    public int findNthDigit(int n) {
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
