/**
 * 41. 缺失的第一个正数
 *
     给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

    示例 1:

    输入: [1,2,0]
    输出: 3
    示例 2:

    输入: [3,4,-1,1]
    输出: 2
    示例 3:

    输入: [7,8,9,11,12]
    输出: 1
    说明:

    你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */

class Solution {
    public int first_missing_positive_41(int[] nums) {
        int n = nums.length;
        boolean contains = false;
        for (int i=0; i<n; i++) {
            if (nums[i]==1) {
                contains=true;
                break;
            }
        }
        if (!contains) return 1;
        if (n==1) return 2;
        for (int i=0; i<n; i++) {
            if (nums[i]<=0 || nums[i]>n) nums[i]=1;
        }
        for (int i=0; i<n; i++) {
            int a = Math.abs(nums[i]);
            if (a==n) nums[0]=-Math.abs(nums[0]);
            else nums[a]=-Math.abs(nums[a]);
        }
        for (int i=1; i<n; i++) {
            if (nums[i]>0) return i;
        }
        if (nums[0]>0) return n;
        return n+1;
    }
}