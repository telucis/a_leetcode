/**
 * 57. 插入区间
 *
    给出一个无重叠的 ，按照区间起始端点排序的区间列表。

    在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

    示例 1:

    输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
    输出: [[1,5],[6,9]]
    示例 2:

    输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    输出: [[1,2],[3,10],[12,16]]
    解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> tmp = new ArrayList<>();
        for (int[] i : intervals) {
            tmp.add(i);
        }
        tmp.add(newInterval);
        Collections.sort(tmp, (a, b)->a[0]-b[0]);
        List<int[]> ans = new ArrayList<>();
        for (int[] i : tmp) {
            if (ans.isEmpty() || ans.get(ans.size()-1)[1] < i[0]) {
                ans.add(i);
            } else if (ans.get(ans.size()-1)[1]<i[1]){
                ans.get(ans.size()-1)[1] = i[1];
            }
        }
        int[][] res = new int[ans.size()][2];
        for (int i=0; i<ans.size(); i++) res[i] = ans.get(i);
        return res; 
    }
}

