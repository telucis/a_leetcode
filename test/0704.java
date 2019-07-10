/**
 * dp-戳气球
 */
//312
public int maxCoins(int[] nums) {
    int[] tnums = new int[nums.length+2];
    int n = 1;
    for (int num : nums) {
        tnums[n] = num;
        n++;
    }
    tnums[0] = tnums[n++] = 1;
    int[][] coins = new int[n][n];
    int low, high, k;
    for (low = n-1; low>=0; low--) {
        for (high=low+2; high<n; high++) {
            if ((high-low) == 2) {
                coins[low][high] = tnums[low] * tnums[(low+high)/2] * tnums[high];
            } else {
                for (k=low+1; k<=high-1; k++) {
                    int tcoins = coins[low][k] + coins[k][high] + tnums[low]*tnums[k]*tnums[high];
                    if (coins[low][high] < tcoins) {
                        coins[low][high] = tcoins;
                    }
                }
            }
        }
    }
    return coins[0][n-1];
}
//1039
public int minScoreTriangulation(int[] A) {
    int[][] dp = new int[A.length][A.length];
    int n = A.length;
    for (int i=n-1; i>=0; i--) {
        for (int j=i+1; j<n; j++) {
            for (int k=i+1; k<j; k++) {
                dp[i][j] = Math.min(dp[i][j]==0 ? Integer.MAX_VALUE : dp[i][j], A[k]*A[i]*A[j]+dp[i][k]+dp[k][j]);
            }
        }
    }
    return dp[0][n-1];
}
/**
 * dp-回文串
 */
//730
int mod = 1000000007;
public int countPalindromicSubsequencs(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i=0; i<n; i++) dp[i][i] = 1;
    char[] chs = s.toCharArray();

    for (int len=1; i<n; len++) {
        for (int i=0; i<n-len; i++) {
            int j=i+len;
            if (chs[i]==chs[j]) {
                int low = i+1, hight = j-1;
                while (low<=hight && chs[low]!=chs[j]) low++;
                while (low<=hight && chs[hight]!=chs[j]) hight--;
                if (low>hight) dp[i][j] = dp[i+1][j-1]*2+2;
                else if (low==hight) dp[i][j] = dp[i+1][j-1]*2+1;
                else dp[i][j] = dp[i+1][j-1]*2 - dp[low+1][hight-1];
            } else {
                dp[i][j] = dp[i][j-1]+dp[i+1][j] - dp[i+1][j-1];
            }
            dp[i][j] = dp[i][j]<0 ? dp[i][j]+mod : dp[i][j]%mod;
        }
    }
    return dp[0][n-1];
}

//516 最长回文子序列
public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i=n-1; i>=0; i--) {
        dp[i][j] = 1;
        for (int j=i+1; j<n; j++) {
            if (s.charAt(i)==s.charAt(j)) {
                dp[i][j] = dp[i+1][j-1]+2;
            } else {
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
    }
    return dp[0][n-1];
}

//5
public int longestPalindrome(Stirng s) {
    if (s.length()==0) return "";
    int n = s.length();
    int[][] dp = new int[n][n];
    int max = 1;
    for (int i=0; i<n; i++){
        dp[i][i] = 1;
        if (i!=n-1 && s.charAt(i)==s.charAt(i+1)) {
            dp[i][i+1]=2;
            max = 2;
        }
    }
    for (int i=n-3; i>=0; i--) {
        for (int j=i+2; j<n; j++) {
            if (s.charAt(i)==s.charAt(j) && dp[i+1][j-1]!=0) {
                dp[i][j] = dp[i+1][j-1]+2;
            } else {
                dp[i][j] = 0;
            }
            max = Math.max(max, dp[i][j]);
        }
    }
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            if (dp[i][j]==max) {
                return s.substring(i, j+1);
            }
        }
    }
    return s.substring(0, 1);
}
//647 回文子串
int count = 0;
public int countSubstrings(String s) {
    if (s==null || s.length()==0) {
        return 0;
    }
    for (int i=0; i<s.length(); i++) {
        extend(s, i, i);
        extend(s, i, i+1);
    }
    return count;
}
private void extend(String s, int l, int r) {
    while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)) {
        count++; l--; r++;
    }
}

/**
 * dfs-base
 */
//39
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> ans = new ArrayList<>();
    Arrays.sort(candidates);
    helper(candidates, 0, target, new LinkedList<>(), ans);
    return ans;
}
private void helper(int[] candidates, int start, int target, LinkedList<Integer> tmp, List<List<Integer>> ans) {
    if (target == 0) {
        ans.add(new ArrayList(tmp));
        return;
    }
    for (int i=start; i<candidates.length; i++) {
        if (candidates[start] > target) break;
        tmp.add(candidates[i]);
        helper(candidates, i, target-candidates[i], tmp, ans);
        tmp.removeLast();
    }
}

//40
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>()
    Arrays.sort(candidates);
    helper(candidates, 0, target, new LinkedList<>(), ans);
    return ans;
}
private void helper(int[] candidates, int start, int target, LinkedList<Integer> tmp, List<List<Integer>> ans) {
    if (target==0) {
        ans.add(new ArrayList(tmp));
        return;
    }
    for (int i=start; i<candidates.length; i++) {
        if (candidates[start] > target) break;
        if (i!=start && candidates[i]==candidates[i-1]) continue;
        tmp.add(candidates[start]);
        helper(candidates, i+1, target-candidates[i], tmp, ans);
        tmp.removeLast();
    }
}

//216
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();
    helper(k, n, 1, tmp, ans);
    return ans;
}
private void helper(int k, int n, int start, LinkedList<>() tmp, List<List<Integer>> ans) {
    if (k==0 && n==0) ans.add(new ArrayList(tmp));
    else if (k==0 || n==0) return;
    else {
        for (int i=start; i<10; i++) {
            if (i>n) break;
            tmp.add(i);
            helper(k-1, n-i, i+1, tmp, ans);
            tmp.removeLast();
        }
    }
}











