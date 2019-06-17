package complete.z_idiot.string;

/**
 * @author karl.wy
 * @date 2019/04/19
 *
 * 字符串中的单词数
 *
    统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

    请注意，你可以假定字符串里不包括任何不可打印的字符。

    示例:

    输入: "Hello, my name is John"
    输出: 5
 */
public class number_of_segments_in_a_string_434 {

    public int countSegments(String s) {
        s = s.trim();
        int count = 0;
        boolean during = false;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != ' ' && !during) {
                count++;
                during = true;
            } else if (s.charAt(i) == ' ') {
                during = false;
            }
        }
        return count;
    }
}
