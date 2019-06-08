package complete.design;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 数据流中的第K大元素
 *
    设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。

    你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。

    示例:

    int k = 3;
    int[] arr = [4,5,8,2];
    KthLargest kthLargest = new KthLargest(3, arr);
    kthLargest.add(3);   // returns 4
    kthLargest.add(5);   // returns 5
    kthLargest.add(10);  // returns 5
    kthLargest.add(9);   // returns 8
    kthLargest.add(4);   // returns 8
    说明:
    你可以假设 nums 的长度≥ k-1 且k ≥ 1。


 */
public class kth_largest_element_in_a_stream_703 {
    class KthLargest {
        /**
         * 维护一个最小堆，堆的元素个数为常量 k，新加入一个元素和堆顶比较，如果比堆顶元素小，丢弃，否则删除堆顶元素，插入新元素。
         */
        final PriorityQueue<Integer> q;
        final int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            q = new PriorityQueue<Integer>(k);
            for (int i : nums) {
                add(i);
            }
        }

        public int add(int val) {
            if (q.size() < k) {
                q.offer(val);
            } else if (q.peek() < val) {
                q.poll();
                q.offer(val);
            }
            return q.peek();
        }
    }
}
