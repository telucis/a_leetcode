package complete.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 优势洗牌
 *
    给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

    返回 A 的任意排列，使其相对于 B 的优势最大化。



    示例 1：

    输入：A = [2,7,11,15], B = [1,10,4,11]
    输出：[2,11,7,15]
    示例 2：

    输入：A = [12,24,8,32], B = [13,25,32,11]
    输出：[24,32,8,12]


    提示：

    1 <= A.length = B.length <= 10000
    0 <= A[i] <= 10^9
    0 <= B[i] <= 10^9

 */
public class advantage_shuffle_870 {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int n = A.length;
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->b[0]-a[0]);
        for (int i=0; i<n; i++) {
            pq.add(new int[]{B[i], i});
        }
        int lo=0, hi=n-1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int idx = cur[1], val = cur[0];
            if (A[hi]>val) {
                res[idx] = A[hi--];
            } else {
                res[idx] = A[lo++];
            }
        }
        return res;
    }
}
