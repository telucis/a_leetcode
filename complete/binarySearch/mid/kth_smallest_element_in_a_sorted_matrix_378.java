package complete.binarySearch.mid;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 有序矩阵中第K小的元素
 *
    给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
    请注意，它是排序后的第k小元素，而不是第k个元素。

    示例:

    matrix = [
    [ 1,  5,  9],
    [10, 11, 13],
    [12, 13, 15]
    ],
    k = 8,

    返回 13。
    说明:
    你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。


 */
public class kth_smallest_element_in_a_sorted_matrix_378 {
    /**
     * binarySearch
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo=matrix[0][0], hi=matrix[n-1][n-1];
        while (lo<hi) {
            int mid=lo+(hi-lo)/2;
            int count = getLessEqual(matrix, mid);
            if (count<k) {
                lo=mid+1;
            } else {
                hi=mid;
            }
        }
        return lo;
    }
    private int getLessEqual(int[][] matrix, int mid) {
        int res = 0;
        int n=matrix.length, i=n-1, j=0;
        while (i>=0 && j<n) {
            if (matrix[i][j] <= mid) {
                res += i+1;
                j++;
            } else {
                i--;
            }
        }
        return res;
    }

    /**
     * heap
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)-> a[0]-b[0]);
        int len = Math.min(k, n);
        for (int i=0; i<len; i++) {
            q.offer(new int[]{matrix[i][0], i, 0});
        }
        int[] res = new int[]{matrix[0][0], 0, 0};
        while (k-->0) {
            res = q.poll();
            int row=res[1], col=res[2];
            if (col==n-1) continue;
            q.offer(new int[]{matrix[row][col+1], row, col+1});
        }
        return res[0];
    }
}
