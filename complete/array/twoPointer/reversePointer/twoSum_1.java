package complete.twoPointer.reversePointer;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/15
 */
public class twoSum_1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(target-nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
