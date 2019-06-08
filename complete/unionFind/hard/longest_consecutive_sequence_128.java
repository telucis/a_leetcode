package complete.unionFind.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/22
 *
 * 最长连续序列
 *
    给定一个未排序的整数数组，找出最长连续序列的长度。

    要求算法的时间复杂度为 O(n)。

    示例:

    输入: [100, 4, 200, 1, 3, 2]
    输出: 4
    解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。

 */
public class longest_consecutive_sequence_128 {

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer i : nums) {
            set.add(i);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : set) {
            map.put(i, i);
        }
        for (Integer i: set) {
            if (set.contains(i-1)) {
                union(map, i-1, i);
            }
        }
        HashMap<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (Integer key : map.keySet()) {
            int p = find(map, key);
            count.put(p, count.getOrDefault(p, 0)+1);
            ans = Math.max(ans, count.get(p));
        }
        return ans;
    }
    private void union(Map<Integer, Integer> map, Integer a, Integer b) {
        int pa = find(map, a);
        int pb = find(map, b);
        if (pa == pb) return;
        map.put(pb, pa);
    }
    private int find(Map<Integer, Integer>  map, Integer p) {
        if (!map.get(p).equals(p)) {
            map.put(p, find(map, map.get(p)));
        }
        return map.get(p);
    }
}
