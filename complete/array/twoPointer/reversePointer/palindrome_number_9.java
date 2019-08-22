package complete.twoPointer.reversePointer;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * 回文数
 *
    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    示例 1:

    输入: 121
    输出: true
    示例 2:

    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:

    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。
    进阶:

    你能不将整数转为字符串来解决这个问题吗？


 */
public class palindrome_number_9 {

    public boolean isPalindrome(int x) {
        String xx = String.valueOf(x);
        int left = 0, right = xx.length()-1;
        while (left<right) {
            if (xx.charAt(left) != xx.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
