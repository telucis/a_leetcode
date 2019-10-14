/**
 * 149. 直线上最多的点数
 *
     给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

    示例 1:

    输入: [[1,1],[2,2],[3,3]]
    输出: 3
    解释:
    ^
    |
    |        o
    |     o
    |  o  
    +------------->
    0  1  2  3  4
    示例 2:

    输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    输出: 4
    解释:
    ^
    |
    |  o
    |     o        o
    |        o
    |  o        o
    +------------------->
    0  1  2  3  4  5  6
 */

class Solution {
    public int maxPoints(int[][] points) {
        if (points==null || points.length==0) return 0;
        Map<Double, Set<Integer>> count = new HashMap<>();
        int n = points.length, ans = 1;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                double d;
                if (points[j][0]==points[i][0]) {
                    d = Integer.MAX_VALUE;
                } else {
                    d = (double)(points[j][1]-points[i][1])/(double)(points[j][0]-points[i][0]);
                }
                Set<Integer> set = count.getOrDefault(d, new HashSet<>());
                set.add(i);
                set.add(j);
                count.put(d, set);
                ans = Math.max(ans, count.get(d).size());
                System.out.println(i+" "+j+" "+d);
            }
        }
        return ans;
    }
}
