package complete.array.mid;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 使数组唯一的最小增量
 *
    给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

    返回使 A 中的每个值都是唯一的最少操作次数。

    示例 1:

    输入：[1,2,2]
    输出：1
    解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
    示例 2:

    输入：[3,2,1,2,1,7]
    输出：6
    解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
    可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
    提示：

    0 <= A.length <= 40000
    0 <= A[i] < 40000

 */
public class minimum_increament_to_make_array_unique_945 {

    /**
     Sort the input array.
     Compared with previous number,
     the current number need to be at least prev + 1.

     Time Complexity: O(NlogN) for sorting
     Space: O(1) for in-space sort

     Note that you can apply "O(N)" sort in sacrifice of space.
     Here we don't talk further about sort complexity.
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0, need = 0;
        for (int a : A) {
            res += Math.max(need-a, 0);
            need = Math.max(a, need) + 1;
        }
        return res;
    }
}
