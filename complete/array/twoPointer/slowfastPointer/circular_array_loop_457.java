package complete.twoPointer.slowfastPointer;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 环形数组循环
 *
    给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动 k 个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。

    确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 > 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。


    示例 1：

    输入：[2,-1,1,2,2]
    输出：true
    解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
    示例 2：

    输入：[-1,2]
    输出：false
    解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
    示例 3:

    输入：[-2,1,-1,-2,-2]
    输出：false
    解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。


    提示：

    -1000 ≤ nums[i] ≤ 1000
    nums[i] ≠ 0
    1 ≤ nums.length ≤ 5000

 */
public class circular_array_loop_457 {

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        if (n<=1) {
            return false;
        }
        for (int i=0; i<n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            // slow/fast pointer
            int j=i, k=getIndex(i, nums);
            while (nums[k]*nums[i]>0 && nums[getIndex(k, nums)]*nums[i]>0) {
                if (j==k) {
                    if (j==getIndex(j, nums)) {
                        break;
                    }
                    return true;
                }
                j=getIndex(j, nums);
                k=getIndex(getIndex(k, nums), nums);
            }
            // loop not found, set all element along the way to 0
            j=i;
            int val = nums[i];
            while (nums[j]*val>0) {
                int next = getIndex(j, nums);
                nums[j] = 0;
                j = next;
            }
        }
        return false;
    }
    private int getIndex(int i, int[] nums) {
        int n = nums.length;
        return i+nums[i]>=0 ? (i+nums[i])%n : n+((i+nums[i])%n);
    }
}
