package complete.twoPointer.partition;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 数组中的第K个最大元素
 *
    在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    示例 1:

    输入: [3,2,1,5,6,4] 和 k = 2
    输出: 5
    示例 2:

    输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    输出: 4
    说明:

    你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。


 */
public class kth_largest_element_in_an_array_215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : nums) {
            q.offer(i);
            if (q.size()>k) {
                q.poll();
            }
        }
        return q.poll();
    }

    /**
     * quickselect
     */
    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        int p = quickSelect(nums, 0, n-1, n-k+1);
        return nums[p];
    }
    private int quickSelect(int[] nums, int lo, int hi, int k) {
        int i=lo, j=hi, pivot=nums[hi];
        while (i<j) {
            if (nums[i++] > pivot) swap(nums, --i, --j);
        }
        swap(nums, i, hi);
        int m=i-lo+1;
        if (m==k) return i;
        else if (m>k) return quickSelect(nums, lo, i-1, k);
        else return quickSelect(nums, i+1, hi, k-m);
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
