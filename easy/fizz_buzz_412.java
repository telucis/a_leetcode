package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/27
 *
 * Fizz Buzz
 *
    写一个程序，输出从 1 到 n 数字的字符串表示。

    1. 如果 n 是3的倍数，输出“Fizz”；

    2. 如果 n 是5的倍数，输出“Buzz”；

    3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。

    示例：

    n = 15,

    返回:
    [
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
    ]

 */
public class fizz_buzz_412 {

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            String tmp = "";
            if (i%3==0) {
                tmp += "Fizz";
            }
            if (i%5==0) {
                tmp += "Buzz";
            }
            if (tmp.isEmpty()) {
                tmp = String.valueOf(i);
            }
            res.add(tmp);
        }
        return res;
    }
}
