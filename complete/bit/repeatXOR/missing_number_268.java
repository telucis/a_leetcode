package complete.bit.repeatXOR;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 缺失数字
 *
    给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

    示例 1:

    输入: [3,0,1]
    输出: 2
    示例 2:

    输入: [9,6,4,2,3,5,7,0,1]
    输出: 8
    说明:
    你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?


 */
public class missing_number_268 {

    public int missingNumber(int[] nums) {
        return bit(nums);
    }

    /**
     * bit
     * ^=
     */
    public int bit(int[] nums) {
        int sum = nums.length;
        for (int i=0; i<nums.length; i++) {
            sum ^= nums[i];
            sum ^= i;
        }
        return sum;
    }

    /**
     * sum
     */
    public int sum(int[] nums) {
        int len = nums.length;
        int sum = len*(len+1)/2;
        for (int i=0; i<len; i++) {
            sum-=nums[i];
        }
        return sum;
    }

}
