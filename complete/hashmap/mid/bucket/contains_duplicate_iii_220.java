package complete.hashmap.mid.bucket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 存在重复元素 III
 *
    给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。

    示例 1:

    输入: nums = [1,2,3,1], k = 3, t = 0
    输出: true
    示例 2:

    输入: nums = [1,0,1,1], k = 1, t = 2
    输出: true
    示例 3:

    输入: nums = [1,5,9,1,5,9], k = 2, t = 3
    输出: false

 */
public class contains_duplicate_iii_220 {

    /**
     As a followup question, it naturally also requires maintaining a window of size k. When t == 0, it reduces to the previous question so we just reuse the solution.

     Since there is now a constraint on the range of the values of the elements to be considered duplicates,
     it reminds us of doing a range check which is implemented in tree data structure and would take O(LogN) if a balanced tree structure is used,
     or doing a bucket check which is constant time. We shall just discuss the idea using bucket here.

     Bucketing means we map a range of values to the a bucket. For example, if the bucket size is 3,
     we consider 0, 1, 2 all map to the same bucket. However, if t == 3, (0, 3) is a considered duplicates but does not map to the same bucket.
     This is fine since we are checking the buckets immediately before and after as well. So, as a rule of thumb,
     just make sure the size of the bucket is reasonable such that elements having the same bucket is immediately considered duplicates or duplicates must lie within adjacent buckets.
     So this actually gives us a range of possible bucket size, i.e. t and t + 1. We just choose it to be t and a bucket mapping to be num / t.

     Another complication is that negative ints are allowed. A simple num / t just shrinks everything towards 0.
     Therefore, we can just reposition every element to start from Integer.MIN_VALUE.

     */

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k<1 || t<0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            long remappedNum = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum/((long)t + 1);
            if (map.containsKey(bucket) ||
                (map.containsKey(bucket-1) && remappedNum-map.get(bucket-1)<=t) ||
                (map.containsKey(bucket+1) && map.get(bucket+1)-remappedNum<=t)) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long)nums[i-k]-Integer.MIN_VALUE) / ((long)t+1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}
