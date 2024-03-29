package complete.twoPointer.slowfastPointer;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 实现strstr
 *
    实现 strStr() 函数。

    给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

    示例 1:

    输入: haystack = "hello", needle = "ll"
    输出: 2
    示例 2:

    输入: haystack = "aaaaa", needle = "bba"
    输出: -1
    说明:

    当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

    对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。


 */
public class implement_strstr_28 {

    /**
     * 同向双指针
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (needle.length() > haystack.length()) return -1;
        int right = 0;
        int index = 0;
        for (int i=0; i<haystack.length(); i++) {
            if (needle.charAt(right) == haystack.charAt(i)) {
                if (right == 0) {
                    index = i;
                }
                if (right == needle.length()-1) {
                    return index;
                }
                right++;
            } else {
                i = i - right;
                right = 0;
            }
        }
        return -1;
    }
}
