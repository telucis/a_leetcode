package complete.twoPointer.reversePointer;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 验证回文串
 *
    给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

    说明：本题中，我们将空字符串定义为有效的回文串。

    示例 1:

    输入: "A man, a plan, a canal: Panama"
    输出: true
    示例 2:

    输入: "race a car"
    输出: false

 */
public class valid_palindrome_125 {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int n = s.length();
        int left = 0, right = n-1;
        while (left < right) {
            if (!judgeValid(s.charAt(left))) {
                left++;
            } else if (!judgeValid(s.charAt(right))) {
                right--;
            } else {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    private boolean judgeValid(char s) {
        if (('0'<= s && s<='9') || ('a'<=s && 'z'>=s)) {
            return true;
        }
        return false;
    }
}
