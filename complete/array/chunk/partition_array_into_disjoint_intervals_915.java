package complete.array.mid.chunk;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 分割数组
 *
    给定一个数组 A，将其划分为两个不相交（没有公共元素）的连续子数组 left 和 right， 使得：

    left 中的每个元素都小于或等于 right 中的每个元素。
    left 和 right 都是非空的。
    left 要尽可能小。
    在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。



    示例 1：

    输入：[5,0,3,8,6]
    输出：3
    解释：left = [5,0,3]，right = [8,6]
    示例 2：

    输入：[1,1,1,0,6,12]
    输出：4
    解释：left = [1,1,1,0]，right = [6,12]


    提示：

    2 <= A.length <= 30000
    0 <= A[i] <= 10^6
    可以保证至少有一种方法能够按题目所描述的那样对 A 进行划分。

 */
public class partition_array_into_disjoint_intervals_915 {
    
    public int partitionDisjoint(int[] A) {
        int N = A.length;
        int[] maxleft = new int[N];
        int[] minright = new int[N];

        int m = A[0];
        for (int i = 0; i < N; ++i) {
            m = Math.max(m, A[i]);
            maxleft[i] = m;
        }

        m = A[N-1];
        for (int i = N-1; i >= 0; --i) {
            m = Math.min(m, A[i]);
            minright[i] = m;
        }

        for (int i = 1; i < N; ++i)
            if (maxleft[i-1] <= minright[i])
                return i;

        throw null;
    }

    public int partitionDisjoint2(int[] A) {
        int first = A[0];
        int max = first;
        int result = 1;
        for (int i=1; i<A.length; i++) {
            if (A[i] < first) {
                if (first != max) {
                    first = max;
                }
                result = i+1;
            } else if (A[i] > max) {
                max = A[i];
            }
        }
        return result;
    }
}
