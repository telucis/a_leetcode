package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 最小移动次数使数组元素相等
 *
    给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。

    示例:

    输入:
    [1,2,3]

    输出:
    3

    解释:
    只需要3次移动（注意每次移动会增加两个元素的值）：

    [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

 */
public class minimum_moves_to_equal_array_elements_453 {

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(min, i);
        }
        int res = 0;
        for (int i : nums) {
            if (i > min) {
                res += i-min;
            }
        }
        return res;
    }
}
