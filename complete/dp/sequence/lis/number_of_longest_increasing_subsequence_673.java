package complete.dp.sequence.lis;

/**
 * @author karl.wy
 * @date 2019/05/19
 *
 * 最长递增子序列的个数
 *
    给定一个未排序的整数数组，找到最长递增子序列的个数。

    示例 1:

    输入: [1,3,5,4,7]
    输出: 2
    解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
    示例 2:

    输入: [2,2,2,2,2]
    输出: 5
    解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
    注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。


 */
public class number_of_longest_increasing_subsequence_673 {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] count = new int[n];
        int max = 1, ans=0;
        for (int i=0; i<n; i++) {
            len[i]=count[i]=1;
            for (int j=0; j<i; j++) {
                if (nums[i]>nums[j]) {
                    if (len[i]==len[j]+1) count[i]+=count[j];
                    if (len[i]<len[j]+1) {
                        len[i] = len[j]+1;
                        count[i] = count[j];
                    }
                }
            }
            if (max==len[i]) ans+=count[i];
            if (max<len[i]) {
                max =len[i];
                ans = count[i];
            }
        }
        return ans;
    }
}
