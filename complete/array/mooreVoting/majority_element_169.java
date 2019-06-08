package complete.array.mooreVoting;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 求众数
 *
    给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

    你可以假设数组是非空的，并且给定的数组总是存在众数。

    示例 1:

    输入: [3,2,3]
    输出: 3
    示例 2:

    输入: [2,2,1,1,1,2,2]
    输出: 2

 */
public class majority_element_169 {

    public int majorityElement(int[] nums) {
        return mooreVoting(nums);
    }

    /**
     * Moore Voting Algorithm
     */
    public int mooreVoting(int[] nums) {
        int num = nums[0];
        int count = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    System.out.println(num);
                    num = nums[i];
                    count = 1;
                }
            }
        }
        return num;
    }

    /**
     * bit
     */
    public int bit(int[] nums) {
        int majority = 0;
        for (int i=0, mask=1; i<32; i++, mask<<=1) {
            int bits = 0;
            for (int num : nums) {
                if ((num&mask) != 0) {
                    bits++;
                }
            }
            if (bits > nums.length/2) {
                majority |= mask;
            }
        }
        return majority;
    }
}
