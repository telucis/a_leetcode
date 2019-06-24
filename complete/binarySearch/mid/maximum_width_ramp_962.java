package complete.binarySearch.mid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 最大宽度坡
 *
    给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。

    找出 A 中的坡的最大宽度，如果不存在，返回 0 。



    示例 1：

    输入：[6,0,8,2,1,5]
    输出：4
    解释：
    最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
    示例 2：

    输入：[9,8,1,0,1,9,4,0,4,1]
    输出：7
    解释：
    最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.


    提示：

    2 <= A.length <= 50000
    0 <= A[i] <= 50000

 */
public class maximum_width_ramp_962 {

    public int maxWidthRamp(int[] A) {
        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (int i=0; i<A.length; i++) {
            if (list.size()==0 || A[i]<A[list.get(list.size()-1)]) {
                list.add(i);
            } else {
                int left = 0, right = list.size()-1, mid = 0;
                while (left < right) {
                    mid = (left+right)/2;
                    if (A[list.get(mid)] <= A[i]) {
                        right = mid;
                    } else {
                        left = mid+1;
                    }
                }
                res = Math.max(res, i-list.get(left));
            }
        }
        return res;
    }
}
