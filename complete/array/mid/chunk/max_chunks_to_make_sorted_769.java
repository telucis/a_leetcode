package complete.array.mid.chunk;

/**
 * @author karl.wy
 * @date 2019/04/30
 *
 * 最多能完成排序的块
 *
    数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。

    我们最多能将数组分成多少块？

    示例 1:

    输入: arr = [4,3,2,1,0]
    输出: 1
    解释:
    将数组分成2块或者更多块，都无法得到所需的结果。
    例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
    示例 2:

    输入: arr = [1,0,2,3,4]
    输出: 4
    解释:
    我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
    然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
    注意:

    arr 的长度在 [1, 10] 之间。
    arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。

 */
public class max_chunks_to_make_sorted_769 {

    /**
     We know that our arr is permutation of array 0,1,2,3,...,array.length-1.
     This means that seeing some value in array we know "proper" index of this, 4 should be under index 4, 2 under 2 and so on.
     We may observe that we are able to sort subarray only if during iterating through array max value in array till now was equals to current index.
     So we need only to keep track of current index, and max value till now, and of course counter. And increment this counter when max==idx.
     */
    public int maxChunksToSorted(int[] arr) {
        int count = 0;
        int max = -1;
        int idx = 0;
        while (idx < arr.length) {
            max = Math.max(max, arr[idx]);
            if (max == idx) {
                count++;
            }
            idx++;
        }
        return count;
    }

    /**
     Algorithm: Iterate through the array, each time all elements to the left are smaller (or equal) to all elements to the right, there is a new chunck.
     Use two arrays to store the left max and right min to achieve O(n) time complexity. Space complexity is O(n) too.
     This algorithm can be used to solve ver2 too.
     */
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i=1; i<n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }
        minOfRight[n-1] = arr[n-1];
        for (int i=n-2; i>=0; i--) {
            minOfRight[i] = Math.min(minOfRight[i+1], arr[i]);
        }
        int res = 0;
        for (int i=0; i<n-1; i++) {
            if (maxOfLeft[i] <= minOfRight[i+1]) {
                res++;
            }
        }
        return res;
    }
}
