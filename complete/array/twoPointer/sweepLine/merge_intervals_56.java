package complete.twoPointer.sweepLine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 合并区间
 *
    给出一个区间的集合，请合并所有重叠的区间。

    示例 1:

    输入: [[1,3],[2,6],[8,10],[15,18]]
    输出: [[1,6],[8,10],[15,18]]
    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    示例 2:

    输入: [[1,4],[4,5]]
    输出: [[1,5]]
    解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

 */
public class merge_intervals_56 {

    public int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) {
            return intervals;
        }
        int[] start = new int[length];
        int[] end = new int[length];
        for (int i=0; i<length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int startIndex = 0, endIndex = 0;
        List<int[]> result = new LinkedList<>();
        while (endIndex < length) {
            if (endIndex==length-1 || start[endIndex+1]>end[endIndex]) {
                result.add(new int[]{start[startIndex], end[endIndex]});
                startIndex = endIndex+1;
            }
            endIndex++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
