package complete.heap;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 分割数组为连续子序列
 *
    输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？



    示例 1：

    输入: [1,2,3,3,4,5]
    输出: True
    解释:
    你可以分割出这样两个连续子序列 :
    1, 2, 3
    3, 4, 5


    示例 2：

    输入: [1,2,3,3,4,4,5,5]
    输出: True
    解释:
    你可以分割出这样两个连续子序列 :
    1, 2, 3, 4, 5
    3, 4, 5


    示例 3：

    输入: [1,2,3,4,4,5]
    输出: False


    提示：

    输入的数组长度范围为 [1, 10000]

 */
public class split_array_into_consecutive_subsequences_659 {

    public boolean isPossible(int[] nums) {
        PriorityQueue<Interval> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.end==b.end) {
                    return a.length-b.length;
                }
                return a.end-b.end;
            }
        );
        for (int num : nums) {
            while (!pq.isEmpty() && pq.peek().end+1 < num) {
                if (pq.poll().length<3) return false;
            }
            if (pq.isEmpty() || pq.peek().end==num) {
                pq.add(new Interval(num, num));
            } else {
                pq.add(new Interval(pq.poll().start, num));
            }
        }
        while (!pq.isEmpty()) {
            if (pq.poll().length<3) {
                return false;
            }
        }
        return true;
    }
    class Interval {
        int start;
        int end;
        int length;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end-start+1;
        }
    }
}
