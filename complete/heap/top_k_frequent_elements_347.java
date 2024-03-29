package complete.heap;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 前K个高频元素
 *
    给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    示例 1:

    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]
    示例 2:

    输入: nums = [1], k = 1
    输出: [1]
    说明：

    你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。

 */
public class top_k_frequent_elements_347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(
            (p1, p2) -> p1.getValue()-p2.getValue()
        );
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            q.offer(m);
            if (q.size()>k) {
                q.poll();
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}
