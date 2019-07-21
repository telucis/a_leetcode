package complete.heap.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/06/13
 *
 * 滑动窗口最大值
 *
    给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。

    返回滑动窗口最大值。

    示例:

    输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    输出: [3,3,5,5,6,7]
    解释:

    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
    1 [3  -1  -3] 5  3  6  7       3
    1  3 [-1  -3  5] 3  6  7       5
    1  3  -1 [-3  5  3] 6  7       5
    1  3  -1  -3 [5  3  6] 7       6
    1  3  -1  -3  5 [3  6  7]      7
    注意：

    你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。

    进阶：

    你能在线性时间复杂度内解决此题吗？

 */
public class sliding_window_maximum_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums==null || nums.length<2) return nums;
        Deque<Integer> list = new LinkedList<>();
        int[] result = new int[nums.length-k+1];

        for (int i=0; i<nums.length; i++) {
            while (!list.isEmpty() && nums[list.peekLast()]<=nums[i]) {
                list.pollLast();
            }
            list.addLast(i);
            if (list.peek()<=i-k) list.poll();
            if (i-k+1>=0) result[i-k+1] = nums[list.peek()];
        }
        return result;
    }

    /**
     * heap
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length==0 || k>nums.length) return new int[0];
        int[] result = new int[nums.length-k+1];
        int i=0, m=0;
        PriorityQueue<Integer> pri = new PriorityQueue<>(k, (o1, o2)->o2-o1);
        for (int j=0; j<k; j++) pri.add(nums[j]);

        for (int z=k; z<nums.length; z++) {
            result[i++] = pri.peek();
            pri.remove(nums[m++]);
            pri.add(nums[z]);
        }
        return result;
    }
}
