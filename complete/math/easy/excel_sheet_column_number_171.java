package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * Excel表列序号
 *
    给定一个Excel表格中的列名称，返回其相应的列序号。

    例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
    示例 1:

    输入: "A"
    输出: 1
    示例 2:

    输入: "AB"
    输出: 28
    示例 3:

    输入: "ZY"
    输出: 701

 */
public class excel_sheet_column_number_171 {

    public int titleToNumber(String s) {
        int sum = 0;
        for (int i=s.length()-1; i>=0; i--) {
            char c = s.charAt(i);
            int tmp = (c-'A'+1);
            sum += tmp * Math.pow(26, (s.length()-i-1));
        }
        return sum;
    }
}
