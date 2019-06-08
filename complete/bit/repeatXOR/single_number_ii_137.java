package complete.bit.repeatXOR;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 只出现一次的数字 II
 *
    给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

    说明：

    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

    示例 1:

    输入: [2,2,3,2]
    输出: 3
    示例 2:

    输入: [0,1,0,1,0,1,99]
    输出: 99

 */
public class single_number_ii_137 {

    /**
     * bit
     * for(bit<32) {count += (nums[i])>>bit & 1;} ans+=(count%3)<<bit
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int bit=0; bit<32; bit++) {
            int count = 0;
            for (int i=0; i<nums.length; i++) {
                count += (nums[i]>>bit) & 1;
            }
            ans += (count%3) << bit;
        }
        return ans;
    }

    /**
     * 状态机
     *
     * 初始：  0 0
     * 1个1   1 0
     * 2个1   0 1
     * 3个1   0 0
     */
    public int singleNumber2(int[] nums) {
        int ones=0, twos=0;
        for (int i=0; i<nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}
