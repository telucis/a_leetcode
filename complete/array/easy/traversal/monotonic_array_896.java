package complete.array.easy.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 单调数列
 *
    如果数组是单调递增或单调递减的，那么它是单调的。

    如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。

    当给定的数组 A 是单调数组时返回 true，否则返回 false。



    示例 1：

    输入：[1,2,2,3]
    输出：true
    示例 2：

    输入：[6,5,4,4]
    输出：true
    示例 3：

    输入：[1,3,2]
    输出：false
    示例 4：

    输入：[1,2,4,5]
    输出：true
    示例 5：

    输入：[1,1,1]
    输出：true


    提示：

    1 <= A.length <= 50000
    -100000 <= A[i] <= 100000

 */
public class monotonic_array_896 {

    public boolean isMonotonic(int[] A) {
        boolean isup = true;
        if (A.length <= 1) return true;
        List<Integer> newA = new ArrayList<>();
        newA.add(A[0]);
        for (int i=1; i<A.length; i++) {
            if (A[i] != A[i-1]) {
                newA.add(A[i]);
            }
        }
        if (newA.size() <= 1) {
            return true;
        }
        if (newA.get(1) < newA.get(0)) {
            isup = false;
        }
        for (int i=2; i<newA.size(); i++) {
            if (isup) {
                if (newA.get(i) < newA.get(i-1)) {
                    return false;
                }
            } else {
                if (newA.get(i) > newA.get(i-1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
