package easy.string;

/**
 * @author karl.wy
 * @date 2019/04/23
 *
 * 转换成小写字母
 *
    实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。



    示例 1：

    输入: "Hello"
    输出: "hello"
    示例 2：

    输入: "here"
    输出: "here"
    示例 3：

    输入: "LOVELY"
    输出: "lovely"

 */
public class to_lower_case_709 {

    public String toLowerCase(String str) {
        char[] list = str.toCharArray();
        String ans = "";
        for (int i=0; i<list.length; i++) {
            if (list[i]>='A' && list[i]<='Z') {
                list[i] += 32;
            }
            ans += String.valueOf(list[i]);
        }
        return ans;
    }
}
