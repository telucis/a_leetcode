package medium;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/07
 *
 * 下一个更大元素 III
 *
    给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。

    示例 1:

    输入: 12
    输出: 21
    示例 2:

    输入: 21
    输出: -1

 */
public class next_greater_element_iii_556 {
    public int nextGreaterElement(int n) {
        char[] number = (n+"").toCharArray();
        int i, j;
        for (i=number.length-1; i>0; i--) {
            if (number[i-1]<number[i]) {
                break;
            }
        }
        if (i==0) {
            return -1;
        }
        int x = number[i-1], smallest = i;
        for (j=i+1; j<number.length; j++) {
            if (number[j]>x && number[j]<=number[smallest]) {
                smallest = j;
            }
        }

        char temp = number[i-1];
        number[i-1] = number[smallest];
        number[smallest] = temp;

        Arrays.sort(number, i, number.length);
        long val = Long.parseLong(new String(number));
        return val <= Integer.MAX_VALUE ? (int)val : -1;
    }
}
