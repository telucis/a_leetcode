package medium.twoPoint.partition;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 摆动排序 II
 *
    给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

    示例 1:

    输入: nums = [1, 5, 1, 1, 6, 4]
    输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
    示例 2:

    输入: nums = [1, 3, 2, 2, 3, 1]
    输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
    说明:
    你可以假设所有输入都会得到有效的结果。

    进阶:
    你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？


 */
public class wiggle_sort_ii_324 {

    public void wiggleSort(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : nums) queue.offer(i);
        int n = nums.length/2;
        while (n>0) {
            queue.poll();
            n--;
        }
        n=nums.length;
        int mid = queue.poll();
        int[] copy = Arrays.copyOf(nums, n);
        for (int i=0, j=0, k=n-1; j<=k;) {
            if (copy[j]<mid) {
                swap(copy, i++, j++);
            } else if (copy[j]>mid) {
                swap(copy, j, k--);
            } else {
                j++;
            }
        }
        int m = (n + 1) >> 1;
        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
    }
    private int newIndex(int index, int n) {
        return (1+2*index) % (n|1);
    }
    private void swap(int[] num, int a, int b) {
        int tmp = num[a];
        num[a] = num[b];
        num[b] = tmp;
    }
}
