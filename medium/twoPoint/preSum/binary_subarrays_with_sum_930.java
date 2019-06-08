package medium.twoPoint.preSum;

import java.util.HashMap;

/**
 * Created by telucis on 2019/5/11.
 *
 * 和相同的二元子数组
 *
     在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。



     示例：

     输入：A = [1,0,1,0,1], S = 2
     输出：4
     解释：
     如下面黑体所示，有 4 个满足题目要求的子数组：
     [1,0,1,0,1]
     [1,0,1,0,1]
     [1,0,1,0,1]
     [1,0,1,0,1]


     提示：

     A.length <= 30000
     0 <= S <= A.length
     A[i] 为 0 或 1

 */
public class binary_subarrays_with_sum_930 {

    public int numSubarraysWithSum(int[] A, int S) {
        int ans = 0, sum=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i=0; i<A.length; i++) {
            sum+=A[i];
            if (map.containsKey(sum-S)) {
                ans+=map.get(sum-S);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return ans;
    }
}
