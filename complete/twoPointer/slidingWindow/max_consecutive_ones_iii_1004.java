package complete.twoPointer.slidingWindow;

/**
 * @author karl.wy
 * @date 2019/05/11
 *
 * 最大连续1的个数 III
 *
    给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

    返回仅包含 1 的最长（连续）子数组的长度。



    示例 1：

    输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
    输出：6
    解释：
    [1,1,1,0,0,1,1,1,1,1,1]
    粗体数字从 0 翻转到 1，最长的子数组长度为 6。
    示例 2：

    输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
    输出：10
    解释：
    [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    粗体数字从 0 翻转到 1，最长的子数组长度为 10。


    提示：

    1 <= A.length <= 20000
    0 <= K <= A.length
    A[i] 为 0 或 1

 */
public class max_consecutive_ones_iii_1004 {

    public int longestOnes(int[] A, int K) {
        int zeroCount=0, start=0, res=0;
        for (int end=0; end<A.length; end++) {
            if (A[end]==0) zeroCount++;
            while (zeroCount>K) {
                if (A[start]==0) zeroCount--;
                start++;
            }
            res = Math.max(res, end-start+1);
        }
        return res;
    }
}
