package complete.math.easy;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 矩形重叠
 *
    矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。

    如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。

    给出两个矩形，判断它们是否重叠并返回结果。

    示例 1：

    输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
    输出：true
    示例 2：

    输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
    输出：false
    说明：

    两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
    矩形中的所有坐标都处于 -10^9 和 10^9 之间。

 */
public class rectangle_overlap_836 {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec2[0] >= rec1[2]) {
            return false;
        } else if (rec2[1] >= rec1[3]) {
            return false;
        } else if (rec2[2] <= rec1[0]) {
            return false;
        } else if (rec2[3] <= rec1[1]) {
            return false;
        }
        return true;
    }
}
