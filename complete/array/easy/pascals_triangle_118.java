package complete.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 杨辉三角
 *
    给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:

    输入: 5
    输出:
    [
    [1],
    [1,1],
    [1,2,1],
    [1,3,3,1],
    [1,4,6,4,1]
    ]

 */
public class pascals_triangle_118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows==0) {
            return res;
        }
        res.add(new ArrayList<Integer>(){{add(1);}});
        for (int i=2; i<=numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);
            for (int j=1; j<i-1; j++) {
                tmp.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }
            tmp.add(1);
            res.add(tmp);
        }
        return res;
    }
}
