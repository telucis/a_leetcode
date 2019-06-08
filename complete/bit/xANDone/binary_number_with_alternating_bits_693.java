package complete.bit.xANDone;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 交替位二进制数
 *
    给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。

    示例 1:

    输入: 5
    输出: True
    解释:
    5的二进制数是: 101
    示例 2:

    输入: 7
    输出: False
    解释:
    7的二进制数是: 111
    示例 3:

    输入: 11
    输出: False
    解释:
    11的二进制数是: 1011
    示例 4:

    输入: 10
    输出: True
    解释:
    10的二进制数是: 1010

 */
public class binary_number_with_alternating_bits_693 {



    public boolean hasAlternatingBits(int n) {
        return bit(n);
    }

    /**
     * bit
     * n&1; n>>>=1;
     */
    public boolean bit(int n) {
        int pre = n&1;
        while(n!=0) {
            if ((n&1)!=pre) return false;
            pre = pre==1?0:1;
            n>>>=1;
        }
        return true;
    }

    public boolean math(int n) {
        boolean even = false;
        if (n%2==0) {
            even = true;
        }
        int cnt = 0;
        while (n > 0) {
            if (cnt%2==0) {
                if (n%2==1 && even) {
                    return false;
                } else if (n%2==0 && !even) {
                    return false;
                }
            } else {
                if (n%2==0 && even) {
                    return false;
                } else if (n%2==1 && !even) {
                    return false;
                }
            }
            cnt++;
            n = n>>1;
        }
        return true;
    }
}
