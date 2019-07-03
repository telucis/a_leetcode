/**
 * 背包
 */
// 1024 视频拼接
public int videoStitching(int[][] clips, int T) {
    int[] dp = new int[T+1];
    Arrays.fill(dp, T+1);
    dp[0] = 0;
    for (int i=1; i<=T; i++) {
        for (int[] c : clips) {
            if (i>=c[0] && i<=c[1]) {
                dp[i] = Math.min(dp[i], dp[c[0]]+1);
            }
        }
        if (dp[i]==T+1) return -1;
    }
    return dp[T];
}

// 377 combinations sum iv
public int combinationSum4(int[] nums, int target) {
    Arrays.sort(nums);
    int[] res = new int[target+1];
    for (int i=1; i<res.length; i++) {
        for (int num : nums) {
            if (num > i) break;
            else if (num==i) res[i] += 1;
            else res[i] += res[i-num];
        } 
    }
    return res[target];
}

// 322 coins change
public int coinChange(int[] coins, int amount) {
    int n = coins.length;
    int[][] dp = new int[n+1][amount+1];
    for (int i=0; i<=n; i++) {
        dp[i][0] = 0;
    }
    for (int i=1; i<=amount; i++) {
        dp[0][i] = -1;
    }
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=amount; j++) {
            dp[i][j] = Integer.MAX_VALUE;
            if (dp[i-1][j]!=-1) dp[i][j] = dp[i-1][j];
            int nums = j/coins[i-1];
            for (int k=1; k<=nums; k++) {
                if (dp[i-1][j-coins[i-1]*k] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-coins[i-1]*k]+k);
                }
            }
            if (dp[i][j]==Integer.MAX_VALUE) dp[i][j]=-1;
        }
    }
    return dp[n][amount];
}

// 518 coins change ii
public int change(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[n+1][amount+1];
    for (int i=0; i<=n; i++) {
        dp[i][0] = 1;
    }
    for (int i=1; i<=amount; i++) {
        dp[0][i] = 0;
    }
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=amount; j++) {
            dp[i][j] = dp[i-1][j];
            int nums = j/coins[i-1];
            for (int k=1; k<=nums; k++) {
                dp[i][j] += dp[i-1][j-coins[i-1]*k];
            }
        }
    }
    return dp[n][amount];
}

// 474 ones and zero
public int findMaxForm(String[] strs, int m, int n) {
    int l = strs.length;
    int[][][] dp = new int[l+1][m+1][n+1];
    for (int i=1; i<=l; i++) {
        int[] nums = calculate(strs[i-1]);
        for (int j=0; j<=m; j++) {
            for (int k=0; k<=n; k++) {
                if (j>=nums[0] && k>=nums[1]) {
                    dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1);
                } else {
                    dp[i][j][k] = dp[i-1][j][k];
                }
            }
        }
    }
    return dp[l][m][n];
}
private int[] calculate(String str) {
    int[] res = new int[2];
    for (char c : str.toCharArray()) {
        if (c=='0') res[0]++;
        else res[1]++;
    }
    return res;
}

// 416 分割等和子集
public boolean canPartition(int[] nums) {
    int n = nums.length, sum=0;
    for (int i : nums) sum+=i;
    if (sum%2!=0) return false;
    sum /= 2;
    boolean[][] dp = new boolean[n+1][sum+1];
    for (int i=0; i<=n; i++) {
        dp[i][0] = true;
    }
    for (int i=1; i<=sum; i++) {
        dp[0][i] = false;
    }
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=sum; j++) {
            dp[i][j] = dp[i-1][j];
            if (dp[i][j]) continue;
            if (j>=nums[i-1]) {
                dp[i][j] = dp[i-1][j-nums[i-1]];
            }
        }
    }
    return dp[n][sum];
}

/**
 * 记忆化
 */
// 140 word break ii
Map<String, List<String>> map = new HashMap<>();
public List<String> wordBreak(String s, List<String> wordDict) {
    if (map.containsKey(s)) return map.get(s);
    LinkedList<String> ans = new LinkedList<>();
    if (s.length()==0) {
        ans.add("");
        return ans;
    }
    for (String word : wordDict) {
        if (s.startsWith(word)) {
            List<String> wordList = wordBreak(s.substring(word.length()), wordDict);
            for (String w : wordList) {
                ans.add(word+" "+w);
            }
        }
    }
    map.put(s, ans);
    return ans;
}

// 688 马在棋盘上的概率
int[][] moves = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
double[][][] cache;
public double knightProbability(int N, int K, int r, int c) {
    cache = new double[N][N][K];
    return helper(N, K, r, c);
}
private double helper(int N, int K, int r, int c) {
    if (r<0 || r>=N || c<0 || c>=N) return 0;
    if (K==0) return 1;
    System.out.println(r+" "+c+" "+K);
    if (cache[r][c][K-1]!=0) return cache[r][c][K-1];
    double rate = 0;
    for (int i=0; i<8; i++) {
        int x = r+moves[i][0], y = c+moves[i][1];
        rate += 0.125 * knightProbability(N, K-1, x, y);
    }
    cache[r][c][K-1] = rate;
    return rate;
}

// 813  最大平均值和的分组
public double largestSumOfAverage(int[] A, int K) {
    int n = A.length;
    double[] sum = new double[n];
    sum[0] = A[0];
    for (int i=1; i<n; i++) {
        sum[i] = sum[i-1]+A[i];
    }
    return dfs(A, 0, K, sum, new double[n][K+1]);
}
private double dfs(int[] A, int start, int k, int[] sum, int[][] dp) {
    int n = A.length;
    if (k==1) return (sum[n-1]-sum[start]+A[start])*1.0/(n-start);
    if (dp[start][k]!=0) return dp[start][k];
    double ans = 0;
    for (int i=start, i<=A.length-k; i++) {
        ans = Math.max(ans, (sum[i]-sum[start]+A[start])*1.0/(i-start+1)+dfs(A, i+1, k-1, sum));
    }
    dp[start][k] = ans;
    return ans;
}

// 808
double[][] memo;
public double soupServing(int N) {
    memo = new double[N+1][N+1];
    return helper(N, N);
}
private double helper(int A, int B) {
    if (A<=0 && B<=0) return 0.5;
    if (A<=0) return 1;
    if (B<=0) return 0;
    if (memo[A][B]!=0) return memo[A][B];
    int[][] methods = {{100, 0}, {75, 25}, {50, 50}, {25, 75}};
    double ans = 0.0;
    for (int[] method : methods) {
        ans += 0.25 * helper(A-method[0], B-method[1]);
    }
    memo[A][B] = ans;
    return ans;
}

// 509 fibonacci number
Map<Integer, Integer> map = new HashMap<>();
public int fib(int N) {
    if (map.containsKey(N)) return map.get(N);
    if (N==0) {
        map.put(0, 0);
        return 0;
    }
    if (N==1) {
        map.put(1, 1);
        return 1;
    }
    int n = fib(N-1)+fib(N-2);
    map.put(N, n);
    return n;
}


/**
 * 博弈
 */

// 464 can i win
Map<String, Boolean> map = new HashMap<>();
boolean[] used;
public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    int sum = (maxChoosableInteger+1)*maxChoosableInteger/2;
    if (sum < desiredTotal) return false;
    if (desiredTotal<=0) return true;

    used = new boolean[maxChoosableInteger+1];
    return helper(desiredTotal);
}
private boolean helper(int desiredTotal) {
    if (desiredTotal<=0) return false;
    String key = format(used);
    if (map.containsKey(key)) return map.get(key);
    for (int i=1; i<=maxChoosableInteger) {
        if (!used[i]) {
            used[i] = true;
            if (!helper(desiredTotal-i)) {
                map.put(key, true);
                return true;
            }
            used[i] = false;
        }
    }
    map.put(key, false);
    return false;
}
private String format(int[] used) {
    StringBuilder sb = new StringBuilder();
    for (int i : used) {
        sb.append(i);
    }
    return sb.toString();
}

// 1025 除数博弈
public boolean divisorGame(int N) {
    boolean[] dp = new boolean[N+1];
    dp[1] = false;
    for (int i=2; i<=N; i++) {
        for (int j=1; j<i; j++) {
            if (i%j==0 && dp[i-j]==false) {
                dp[i] = true;
            }
        }
    }
    return dp[N];
}

// 292 nim game
// 486  预测赢家
public boolean predictTheWinner(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];
    for (int i=0; i<n; i++) dp[i][i] = nums[i];
    for (int len=1; len<n; len++) {
        for (int i=0; i<n-len; i++) {
            int j=i+len;
            dp[i][j] = Math.max(num[i]-dp[i+1][j], num[j]-dp[i][j-1]);
        }
    }
    return dp[0][n-1]>=0;
}

// 877 stone game
public boolean stoneGame(int[] piles) {
    int n = piles.length;
    int[][] dp = new int[n][n];
    int sum = 0;
    for (int i=0; i<n; i++) {
        sum += piles[i];
        dp[i][i] = piles[i];
        if (i<n-1) dp[i][i+1] = Math.max(dp[i][i], dp[i][i+1]);
    }
    for (int l=2; l<n; l++) {
        for (int k=0; k<n-l; k++) {
            int i=k, j=l+k;
            dp[i][j] = Math.max(
                Math.min(dp[i+2][j], dp[j+1][j-1])+piles[i],
                Math.min(dp[i+1][j-1], dp[i][j-2])+piles[j]
            );
        }
    }
    return dp[0][n-1]>=sum/2;
}

/**
 * 坐标
 */
// 413
public int numberOfArithmeticSlices(int[] A) {
    int n = A.length;
    if (n<3) return 0;
    int[] dp = new int[n];
    int result = 0;
    for (int i=2; i<n; i++) {
        if (A[i]-A[i-1]==A[i-1]-A[i-2]) {
            dp[i] = dp[i-1] +1;
        }
        result += dp[i];
    }
    return result;
}

// 70 爬楼梯
public int climbStairs(int n) {
    if (n<=2) return n;
    int[] dp = new int[n];
    dp[0] = 1;
    dp[1] = 2;
    for (int i=2; i<n; i++) {
        dp[i] = dp[i-1]+dp[i-2];
    }
    return dp[n-1];
}

// 91 解码方法
public int numDecodings(String s) {
    int n = s.length;
    if (n==0) return 0;
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    for (int i=2; i<=n; i++) {
        if (s.charAt(i-1) != '0') dp[i] = dp[i-1];
        int twoDigits = Integer.parseInt(s.substring(i-2, i));
        if (twoDigits>=10 && twoDigits<=26) {
            dp[i] += dp[i-2];
        }
    }
    return dp[n];
}

// 639 解码方法II
int mod = 1000000007;
public int numDecodings(String s) {
    int n = s.length();
    if (n==0) return 0;
    long[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = s.charAt(0)=='0' ? 0 : (s.charAt(0)=='*' ? 9 : 1);
    for (int i=2; i<=n; i++) {
        //todu
    }
    return int(dp[n-1]%mod);
}

// 740 删除与获得
public int deleteAndEarn(int[] nums) {
    int[] count = new int[10001];
    for (int n : nums) {
        count[n] += n;
    }
    int[] dp = new int[10003];
    for (int i=10000; i>=0; i--) {
        dp[i] = Math.max(count[i]+dp[i+2], dp[i+1]);
    }
    return dp[0];
}

//790 多米诺和托米诺
public int numTilings(int N) {
    int mod = 1000000007;
    long[] dp = new int[N+1];
    dp[1] = 1; dp[2] = 2; dp[3] = 5;
    for (int i=4; i<=N; i++) {
        dp[i] = dp[i-1] * 2 + dp[i-3];
        dp[i] %= mod;
    }
    return (int)dp[N];
}

// 198 打家劫舍
public int rob(int[] nums) {
    int n = nums.length;
    if (n==0) return 0;
    int[] dp = new int[n];
    dp[0] = nums[0];
    if (n==1) return nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i=2; i<n; i++) {
        dp[2] = Math.max(nums[i]+dp[i-2], dp[i-1]);
    }
    return dp[n-1];
}

// 213 打家劫舍II
public int rob(int[] nums) {
    int n = nums.length;
    if (n==0) return 0;
    if (n==1) return nums[0];
    if (n==2) return Math.max(nums[1], nums[0]); 
    int[] dp1 = new int[n-1], dp2 = new int[n-1];
    dp1[0] = nums[0], dp1[1] = Math.max(nums[1], nums[0]); 
    dp2[0] = nums[1], dp2[1] = Math.max(nums[1], nums[2]); 
    for (int i=2; i<n-1; i++) {
        dp1[i] = Math.max(nums[i]+dp[i-2], dp[i-1]);
    }
    for (int i=3; i<n; i++) {
        dp2[i-1] = Math.max(nums[i]+dp[i-3], dp[i-2]);
    }
    return Math.max(dp1[n-2], dp2[n-2]);
}

// 337 打家劫舍III
Map<TreeNdoe, Integer> cache = new HashMap<>();
public int rob(TreeNode root) {
    int ans = 0;
    if (root == null) return 0;
    if (cache.containsKey(root)) return map.get(root);
    if (root.left != null) {
        ans += rob(root.left.left)+rob(root.left.right);
    }
    if (root.right != null) {
        ans += rob(root.right.left)+rob(root.right.right);
    }
    ans = Math.max(ans+root.val, rob(left)+rob(right));
    cache.put(root, ans);
    return ans;
}

// 746 最小花费爬楼梯
// 837 新21点
public double new21Game(int N, int K, int W) {
    if (K==0 || N>=K+W) return 1;
    double[] dp = new double[N+1];
    double Wsum = 1, res = 0;
    dp[0] = 1;
    for (int i=1; i<=N; i++) {
        dp[i] = Wsum/W;
        if (i<K) Wsum+=dp[i];
        else res+=dp[i];
        if (i-W>=0) Wsum-=dp[i-W];
    }
    return res;
}

// 467 环绕字符串中惟一的子字符串
public int findSumstringInWrapraoundString(String p) {
    int[] count = new int[26];
    int maxLengthCur = 0;
    for (int i=0; i<p.length(); i++) {
        if (i>0 && (p.charAt(i)==p.charAt(i-1)+1 || p.charAt(i)+25==p.charAt(i)-1)) {
            maxLengthCur++;
        } else {
            maxLengthCur = 1;
        }
        int index = p.charAt(i)-'a';
        count[index] = Math.max(count[index], maxLengthCur);
    }
    int sum = 0;
    for (int i=0; i<26; i++) sum+=count[i];
    return sum;
}

/**
 * 坐标-跳跃
 */
// 55 jump game
public boolean canJump(int[] nums) {
    int n = nums.length;
    boolean[] dp = new boolean[nums.length];
    dp[0] = true;
    for (int i=0; i<nums.length; i++) {
        if (dp[i]) {
            int end = nums[i]+i > n ? n : nums[i]+i;
            for (int j=i; j<end; j++) {
                dp[j] = true;
            }
        }
        if (dp[n-1]) return true;
    }
    return false;
}
public boolean canJump_greedy(int[] nums) {
    int n = nums.length;
    int end = nums[0];
    for (int i=1; i<=end; i++) {
        if (end >= n-1) return true;
        end = Math.max(end, nums[i]+i);
    }
    return false;
}
// 45 jump game ii
public int jump(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i=0; i<n; i++) {
        if (dp[i]==Integer.MAX_VALUE) return -1;
        for (int j=1; j<=nums[i]; j++) {
            if (i+j<n) dp[i+j] = Math.min(dp[i+j], dp[i]+1);
            if (i+j==n-1) return dp[n-1];
        }
    }
    return dp[n-1];
}
public int jump_greedy(int[] nums) {
    if (nums==nums || nums.length==0) return 0;
    int n = nums.length;
    int step = 0, start=0, end=0;
    while (end<n-1) {
        jump++;
        int farest = end;
        for (int i=start; i<=end; i++) {
            farest = Math.max(farest, nums[i]+i);
        }
        start = end+1;
        end = farest;
    }
    return step;
}

/**
 * 矩阵
 */
// 764 最大加号标志
public int orderOfLargestPlusSign(int N, int[][] mines) {
    int[][] grid = new int[N][N];
    for (int[] arr : grid) Arrays.fill(arr, 1);
    for (int[] arr ：mines) grid[arr[0]][arr[1]] = 0;
    for (int i=0; i<N; i++) {
        int count = 0;
        for (int j=0; j<N; j++) {
            if (grid[i][j]!=0) count++;
            else count=0;
            grid[i][j] = count;
        }
        count = 0;
        for (int j=N-1; j>=0; j--) {
            if (grid[i][j]!=0) count++;
            else count=0;
            grid[i][j] = Math.min(grid[i][j], count);
        }
    }
    int ans = 0;
    for (int j=0; j<N; j++) {
        int count = 0;
        for (int i=0; i<N; i++) {
            if (grid[i][j]!=0) count++;
            else count=0;
            grid[i][j] = Math.min(grid[i][j], count);
        }
        count = 0;
        for (int i=N-1; i>=0; i--) {
            if (grid[i][j]!=0) count++;
            else count=0;
            grid[i][j] = Math.min(grid[i][j], count);
            ans = Math.max(grid[i][j], ans);
        }
    }
    return ans;
}

//221 最大正方形
public int maximalSquare(char[][] matrix) {
    if (matrix.length==0 || matrix[0].length==0) return 0;
    int m = matrix.length, n = matrix[0].length;
    int[][] dp = new int[m][n];
    int ans = 0;
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            if (matrix[i][j]=='1') {
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
            } else{
                dp[i][j] = 0;
            }
            ans = Math.max(ans, dp[i][j]);
        }
    }
    return ans*ans;
}

//64 最小路径和
//62 不同路径
//63 不同路径II
//120 三角形最小路径和
//931 下降路径最小和

//935 骑士拨号器
public int knightDialer(int N) {
    int mode = 1000000007;
    long[] ans = new long[10];
    Arrays.fill(ans, 1);
    while(N-->1) {
        long[] n = new long[10];
        n[0] = (ans[4]+ans[6])%mod;
        n[1] = (ans[6]+ans[8])%mod;
        n[2] = (ans[7]+ans[9])%mod;
        n[3] = (ans[4]+ans[8])%mod;
        n[4] = (ans[0]+ans[3]+ans[9])%mod;
        n[5] = 0;
        n[6] = (ans[0]+ans[1]+ans[7])%mod;
        n[7] = (ans[2]+ans[6])%mod;
        n[8] = (ans[1]+ans[3])%mod;
        n[9] = (ans[2]+ans[4])%mod;
        ans = n;
    }
    long res = 0;
    for (long i : ans) {
        res = (res+i)%mod;
    }
    return (int)res;
}

//963 最低票价
public int mincostTickets(int[] days, int[] costs) {
    boolean[] dayIncludes = new boolean[366];
    for (int day : days) dayIncludes[day] = true;
    int[] minCost = new int[366];
    minCost[0] = 0;
    for (int day=1; day<=365; day++) {
        if (!dayIncludes[day]) {
            minCost[day] = minCost[day-1];
            continue;
        }
        int min = minCost[day-1] + costs[0];
        min = Math.min(min, minCost[day-7]+costs[1]);
        min = Math.min(min, minCost[day-30]+costs[2]);
        minCost = min;
    }
    return minCost[365];
}

//801  使序列递增的最小交换次数
// 1 3 5 4
// 1 2 3 7
public int minSwap(int[] A, int[] B) {
    int swapRecode=1, fixRecode=0;
    for (int i=1; i<A>length; i++) {
        if (A[i-1]>=B[i] || B[i-1]>=A[i]) {
            swapRecode++;
        } else if (A[i-1]>=A[i] || B[i-1]>=B[i]) {
            int tmp = swapRecode;
            swapRecode = fixRecode+1;
            fixRecode = tmp;
        } else {
            int min = Math.min(swapRecode, fixRecode);
            swapRecode = min+1;
            fixRecode = min;
        }
    }
    return Math.min(swapRecode, fixRecode);
}

/**
 * 
 */





