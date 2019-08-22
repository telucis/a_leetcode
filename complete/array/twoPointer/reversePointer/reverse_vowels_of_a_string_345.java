package complete.twoPointer.reversePointer;

import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 反转字符串中的元音字母
 *
    编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

    示例 1:

    输入: "hello"
    输出: "holle"
    示例 2:

    输入: "leetcode"
    输出: "leotcede"
    说明:
    元音字母不包含字母"y"。


 */
public class reverse_vowels_of_a_string_345 {

    /**
     * 反向双指针
     */
    public String reverseVowels(String s) {
        HashSet<Character> set = new HashSet<Character>(){{
            add('a'); add('e'); add('i'); add('o'); add('u');
            add('A'); add('E'); add('I'); add('O'); add('U');
        }};
        char[] ss = s.toCharArray();
        int left = 0, right = s.length()-1;
        while (left<right) {
            if (!set.contains(s.charAt(left))) {
                left++;
            } else if (!set.contains(s.charAt(right))) {
                right--;
            } else {
                char tmp = ss[left];
                ss[left] = ss[right];
                ss[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(ss);
    }
}
