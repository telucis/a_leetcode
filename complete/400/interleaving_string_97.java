/**
 * 97. 交错字符串
 *
     给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

    示例 1:

    输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    输出: true
    示例 2:

    输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    输出: false
 */

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()+s2.length()!=s3.length()) return false;
        return helper(s1, s2, s3, 0, 0, 0);
    }
    private boolean helper(String s1, String s2, String s3, int i, int j, int k) {
        if (k==s3.length()) return true;
        if (s1.length()==0 || i==s1.length()) {
            if (s3.charAt(k) != s2.charAt(j)) return false;
            return helper(s1, s2, s3, i, j+1, k+1);
        }
        if (s2.length()==0 || j==s2.length()) {
            if (s3.charAt(k) != s1.charAt(i)) return false;
            return helper(s1, s2, s3, i+1, j, k+1);
        }
        if (s3.charAt(k)==s1.charAt(i) && helper(s1, s2, s3, i+1, j, k+1)) return true;
        if (s3.charAt(k)==s2.charAt(j) && helper(s1, s2, s3, i, j+1, k+1)) return true;
        return false;
    }
}

