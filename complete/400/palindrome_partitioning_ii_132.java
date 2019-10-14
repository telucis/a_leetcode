/**
 * 132. 分割回文串 II
 *
    给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

    返回符合要求的最少分割次数。

    示例:

    输入: "aab"
    输出: 1
    解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 */

class Solution {
    public int minCut(String s) {
        if(s == null || s.length() <= 1)
            return 0;
        int len = s.length();
        int dp[] = new int[len];
        Arrays.fill(dp, len-1);
        for(int i = 0; i < len; i++){
            // 注意偶数长度与奇数长度回文串的特点
            mincutHelper(s , i , i , dp);  // 奇数回文串以1个字符为中心
            mincutHelper(s, i , i+1 , dp); // 偶数回文串以2个字符为中心
        }
        return dp[len-1];
    }
    private void mincutHelper(String s, int i, int j, int[] dp){
        int len = s.length();
        while(i >= 0 && j < len && s.charAt(i) == s.charAt(j)){
            dp[j] = Math.min(dp[j] , (i==0?-1:dp[i-1])+1);
            i--;
            j++;
        }
    }
}

