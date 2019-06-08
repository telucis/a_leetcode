package complete.twoPointer.slowfastPointer;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 移动零
 *
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    示例:

    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]
    说明:

    必须在原数组上操作，不能拷贝额外的数组。
    尽量减少操作次数。

 */
public class move_zeros_283 {

    /**
     * 同向双指针
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                index++;
            }
        }
    }
}
