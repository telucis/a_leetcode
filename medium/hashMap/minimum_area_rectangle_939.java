package medium.hashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by telucis on 2019/5/12.
 *
 * 最小面积矩形
 *
     给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，其中矩形的边平行于 x 轴和 y 轴。

     如果没有任何矩形，就返回 0。



     示例 1：

     输入：[[1,1],[1,3],[3,1],[3,3],[2,2]]
     输出：4
     示例 2：

     输入：[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
     输出：2


     提示：

     1 <= points.length <= 500
     0 <= points[i][0] <= 40000
     0 <= points[i][1] <= 40000
     所有的点都是不同的。

 */
public class minimum_area_rectangle_939 {

    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) map.put(p[0], new HashSet<>());
            map.get(p[0]).add(p[1]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0]==p2[0] || p1[1]==p2[1]) continue;
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs((p2[0]-p1[0]) * (p2[1]-p1[1])));
                }
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }
}
