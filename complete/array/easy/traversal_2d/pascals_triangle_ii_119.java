package complete.array.easy.traversal_2d;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 杨辉三角 II
 *
 *
    给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。



    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:

    输入: 3
    输出: [1,3,3,1]
    进阶：

    你可以优化你的算法到 O(k) 空间复杂度吗？


 */
public class pascals_triangle_ii_119 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        if (rowIndex == 0) return res;
        res.add(1);
        if (rowIndex == 1) {
            return res;
        }
        for (int i=2; i<=rowIndex; i++) {
            List<Integer> newRes = new ArrayList<>();
            newRes.add(1);
            for (int j=1; j<i; j++) {
                newRes.add(res.get(j-1)+res.get(j));
            }
            newRes.add(1);
            res = newRes;
        }
        return res;
    }
}
