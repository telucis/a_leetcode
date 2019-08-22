package complete.twoPointer.reversePointer;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 验证回文字符串 Ⅱ
 *
    给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

    示例 1:

    输入: "aba"
    输出: True
    示例 2:

    输入: "abca"
    输出: True
    解释: 你可以删除c字符。
    注意:

    字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。

 */
public class valid_palindrome_ii_680 {

    public boolean validPalindrome(String s) {
        return judgePalindrome(s, 0, s.length()-1, true);
    }

    private boolean judgePalindrome(String s, int l, int r, boolean canDel) {
        int left = l, right = r;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (canDel) {
                    boolean leftDel = judgePalindrome(s, left+1, right, false);
                    boolean rightDel = judgePalindrome(s, left, right-1, false);
                    return leftDel || rightDel;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
