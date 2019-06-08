package complete.bit.repeatXOR;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/15
 *
 * 只出现一次的数字
 *
    给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

    说明：

    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

    示例 1:

    输入: [2,2,1]
    输出: 1
    示例 2:

    输入: [4,1,2,1,2]
    输出: 4
 */
public class single_number_136 {
    /**
     * 位运算
     *
     * 1. 交换律：a ^ b ^ c <=> a ^ c ^ b
     * 2. 任何数于0异或为任何数 0 ^ n => n
     * 3. 相同的数异或为0: n ^ n => 0
     */
    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int i=0; i<nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }


    /**
     * hashmap
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }
}
