package complete.bit.repeatXOR;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 只出现一次的数字 III
 *
    给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

    示例 :

    输入: [1,2,1,3,2,5]
    输出: [3,5]
    注意：

    结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
    你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

 */
public class single_number_iii_260 {

    /**
     * diff&=-diff; num[i]&diff
     */
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff=0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0};
        for (int num : nums) {
            if ((num & diff)==0) {
                rets[0] ^= num;
            } else {
                rets[1] ^= num;
            }
        }
        return rets;
    }
}
