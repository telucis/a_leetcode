package easy.string;

import com.sun.deploy.util.StringUtils;

/**
 * @author karl.wy
 * @date 2019/04/23
 *
 * 反转字符串中的单词 III
 *
    给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

    示例 1:

    输入: "Let's take LeetCode contest"
    输出: "s'teL ekat edoCteeL tsetnoc"
    注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。


 */
public class reverse_words_in_a_string_iii_557 {

    public String reverseWords(String s) {
        String ans = "";
        String[] list = s.split(" ");
        for (int i=0; i<list.length; i++) {
            ans += reverse(list[i]);
            if (i != list.length-1) {
                ans += " ";
            }
        }
        return ans;
    }
    private String reverse(String s) {
        int left = 0;
        int right = s.length()-1;
        char[] res = new char[s.length()];
        while (left<right) {
            res[left] = s.charAt(right);
            res[right] = s.charAt(left);
            left++;
            right--;
        }
        if (left == right) {
            res[left] = s.charAt(left);
        }
        String ss = "";
        for (int i=0; i<res.length; i++) {
            ss += String.valueOf(res[i]);
        }
        return ss;
    }
}
