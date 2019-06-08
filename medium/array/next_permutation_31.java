package medium.array;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 下一个排列
 *
    实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

    必须原地修改，只允许使用额外常数空间。

    以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
 */
public class next_permutation_31 {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int j = nums.length-1;
        while (j-1>=0 && nums[j-1]>=nums[j]) {
            j--;
        }
        if (j==0) {
            for (int i=0; i<nums.length/2; i++) {
                swap(nums, i, nums.length-i-1);
            }
            return;
        }
        int r = nums.length-1;
        while (r >= j) {
            if (nums[r] > nums[j-1]) {
                break;
            }
            r--;
        }
        swap(nums, j-1, r);
        int i=0;
        while (j+i<nums.length-1-i) {
            swap(nums, j+i, nums.length-1-i);
            i++;
        }
        return;
    }
    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
