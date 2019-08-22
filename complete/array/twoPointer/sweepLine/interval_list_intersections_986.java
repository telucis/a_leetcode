package complete.twoPointer.sweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 区间列表的交集
 *
    给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。

    返回这两个区间列表的交集。

    （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）



    示例：



    输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
    输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    注意：输入和所需的输出都是区间对象组成的列表，而不是数组或列表。


    提示：

    0 <= A.length < 1000
    0 <= B.length < 1000
    0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

 */
public class interval_list_intersections_986 {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<Node> list = new ArrayList<>();
        for (int i=0; i<A.length; i++) {
            list.add(new Node(A[i][0], true));
            list.add(new Node(A[i][1], false));
        }
        for (int i=0; i<B.length; i++) {
            list.add(new Node(B[i][0], true));
            list.add(new Node(B[i][1], false));
        }
        Collections.sort(list, (a, b)-> {
            if (a.val.equals(b.val)) {
                return a.isStart ? -1:1;
            }
            return a.val.compareTo(b.val);
        });
        List<int[]> ans = new ArrayList<>();
        int start=0, count=0;
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).isStart) {
                count++;
                if (count==2) {
                    start=list.get(i).val;
                }
            } else {
                if (count==2) {
                    ans.add(new int[]{start, list.get(i).val});
                }
                count--;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
    class Node {
        Integer val;
        Boolean isStart;
        public Node(Integer val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
    }
}
