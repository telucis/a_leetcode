package easy.binarySearch;

/**
 * @author karl.wy
 * @date 2019/04/16
 *
 * 排列硬币
 *
    你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。

    给定一个数字 n，找出可形成完整阶梯行的总行数。

    n 是一个非负整数，并且在32位有符号整型的范围内。

    示例 1:

    n = 5

    硬币可排列成以下几行:
    ¤
    ¤ ¤
    ¤ ¤

    因为第三行不完整，所以返回2.
    示例 2:

    n = 8

    硬币可排列成以下几行:
    ¤
    ¤ ¤
    ¤ ¤ ¤
    ¤ ¤

    因为第四行不完整，所以返回3.

 */
public class arranging_coins_441 {

    public int arrangeCoins(int n) {
        int res = 0;
        while (n>0) {
            if (n <= res) {
                break;
            }
            res++;
            n -= res;
        }
        return res;
    }

    /**
     * 二分
     */
    public int arrangeCoins2(int n) {
        int low = 0, high = Math.min(n, (1 << 16) - 1), mid = 0, sum = 0;
        while(low <= high){
            mid = (low + high) >> 1;
            sum = ((mid & 1) == 0) ? (mid/2) * (mid + 1) : ((mid + 1)/2) * mid;
            if(sum == n){
                return mid;
            }else if(sum > n){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return sum > n ? mid-1 : mid;
    }
}
