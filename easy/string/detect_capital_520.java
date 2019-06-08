package easy.string;

/**
 * @author karl.wy
 * @date 2019/04/22
 *
 * 检测大写字母
 *
    给定一个单词，你需要判断单词的大写使用是否正确。

    我们定义，在以下情况时，单词的大写用法是正确的：

    全部字母都是大写，比如"USA"。
    单词中所有字母都不是大写，比如"leetcode"。
    如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
    否则，我们定义这个单词没有正确使用大写字母。

    示例 1:

    输入: "USA"
    输出: True
    示例 2:

    输入: "FlaG"
    输出: False
    注意: 输入是由大写和小写拉丁字母组成的非空单词。


 */
public class detect_capital_520 {

    public boolean detectCapitalUse(String word) {
        int type = 0;
        char firstWord = word.charAt(0);
        if (firstWord>='A' && firstWord<='Z' && word.length()>=2) {
            if (word.charAt(1)>='A' && word.charAt(1)<='Z') {
                type = 1;
            } else {
                type = 2;
            }
        }
        for (int i=1; i<word.length(); i++) {
            char s = word.charAt(i);
            if (type == 0) {
                if (s<'a' || s>'z') {
                    return false;
                }
            } else if (type == 1) {
                if (s<'A' || s>'Z') {
                    return false;
                }
            } else {
                if (s<'a' || s>'z') {
                    return false;
                }
            }
        }
        return true;
    }
}
