package easy.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 数组形式的整数加法
 *
    对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。

    给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。



    示例 1：

    输入：A = [1,2,0,0], K = 34
    输出：[1,2,3,4]
    解释：1200 + 34 = 1234
    解释 2：

    输入：A = [2,7,4], K = 181
    输出：[4,5,5]
    解释：274 + 181 = 455
    示例 3：

    输入：A = [2,1,5], K = 806
    输出：[1,0,2,1]
    解释：215 + 806 = 1021
    示例 4：

    输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
    输出：[1,0,0,0,0,0,0,0,0,0,0]
    解释：9999999999 + 1 = 10000000000


    提示：

    1 <= A.length <= 10000
    0 <= A[i] <= 9
    0 <= K <= 10000
    如果 A.length > 1，那么 A[0] != 0

 */
public class add_to_array_form_of_integer_989 {

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> ans = new LinkedList<>();
        int i=A.length-1, extra = 0;
        while (K!=0 || i>=0) {
            int val = extra;
            if (i>=0) {
                val += A[i--];
            }
            if (K!=0) {
                val += K%10;
                K /= 10;
            }
            ans.addFirst(val%10);
            extra = val > 9 ? 1: 0;
        }
        if (extra == 1) {
            ans.addFirst(1);
        }
        return ans;
    }
}
