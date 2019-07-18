/**
 * 序列-lis
 */
//873
public int lenLongestFibSubseq(int[] A) {
    int n = A.length;
    int[][] dp = new int[n][n];
    Map<Integer, Integer> index = new HashMap<>();
    for (int j=0; j<n; j++) {
        index.put(A[j], j);
        for (int i=0; i<j; i++) {
            int k = index.getOrDefault(A[j]-A[i], -1);
            if (A[j]-A[i]<A[i] && k>0) {
                dp[i][j] = dp[k][i]+1;
            } else {
                dp[i][j] = 2;
            }
            res = Math.max(res, dp[i][j]);
        }
    }
    return res > 2 ? res : 0;
}

//650 只有两个键的键盘
public int minSteps(int n) {
    int[] dp = new int[n+1];
    for (int i=2; i<=n; i++) {
        dp[i] = i;
        for (int j=2; j<i/2; j+) {
            if (i%j==0) {
                dp[i] = dp[i/j]+j;
                break;
            }
        }
    }
    return dp[n];
}

//448 等差数列划分II-子序列
public int numberOfArithmeticSlices(int[] A) {
    int n = A.length;
    Map<Integer, Integer>[] map = new Map[n];
    for (int i=1; i<A.length; i++) {
        map[i] = new HashMap<>();
        for (int j=0; j<i; j++) {
            double diff = A[i]-A[j];
            if (diff<=Integer.MIN_VALUE || diff>Integer.MAX_VALUE) continue;
            int d = (int)diff;
            int c1 = map[i].getOrDefault(d, 0);
            int c2 = map[j].getOrDefault(d, 0);
            map[i].put(d, c1+c2+1);
            ans += c2;
        }
    }
    return ans;
}

//1027 最长等差数列
public int longestArithSeqLength(int[] A) {
    int n = A.length;
    if (n<=2) return n;
    Map<Integer, Integer>[] dp = new HashMap[n];
    int ans = 2;
    for (int i=0; i<n; i++) {
        dp[i] = new HashMap<>();
        for (int j=0; j<i; j++) {
            int d = A[i]-A[j];
            int len = 2;
            if (dp[j].containsKey(d)) {
                len = dp[j].get(d)+1;
            }
            dp[i].put(d, Math.max(len, dp[i].getOrDefault(d, 2)));
            ans = Math.max(ans, dp[i].get(d));
        }
    }
    return ans;
}

//334 递增的三元子序列
public boolean increasingTriplet(int[] nums) {
    int a=Integer.MAX_VALUE, b=Integer.MAX_VALUE;
    for (int num : nums) {
        if (num<=a) a = num;
        else if (num<=b) b = num;
        else return true;
    }
    return false;
}
1
//368 最大整除子集
public List<Integer> largestDivisibleSubset(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    Arrays.sort(nums);
    int n = nums.length, max = 1;
    int[][] dp = new int[n][3];
    for (int i=0; i<n; i++) dp[i] = new int[]{1, i, i};
    for (int i=0; i<n; i++) {
        for (int j=0; j<i; j++) {
            if (nums[i]%nums[j]==0 || dp[i][0]<dp[j][0]+1) {
                dp[i][0] = dp[j][0]+1;
                dp[i][1] = j;
                max = Math.max(max, dp[i][0]);
            }
        }
    }
    for (int i=0; i<n; i++) {
        if (dp[i][0] == max) {
            int[] peek = dp[i];
            while (peek[1] != peek[2]) {
                ans.add(nums[peek[2]]);
                peek = dp[peek[1]];
            }
            ans.add(nums[peek[2]]);
            break;
        }
    }
    Collections.reverse(ans);
        retuan ans;
}

//300 最长上升子序列
//646 最长数对链
//673 最长递增子序列的个数
//354 俄罗斯信封套娃

/**
 * 序列-股票
 */
//121 买卖股票的最佳时机
//122 买卖股票的最佳时机ii
//309 最佳买卖股票时机含冷冻期
public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n==0) return 0;
    int[] sell = new int[n], buy = new int[n], cool = new int[n];
    buy[0] = -prices[0];
    for (int i=1; i<prices.length; i++) {
        sell[i] = Math.max(buy[i-1]+prices[i], sell[i-1]);
        buy[i] = Math.max(cool[i-1]-private[i], buy[i-1]);
        cool[i] = Math.max(sell[i-1], cool[i-1]);
    }
    return sell[n-1];
}

//714 买卖股票的最佳时机含手续费
public int maxProfit(int[] prices, int fee) {
    int n = prices.length;
    if (n<2) return 0;
    int[] dp1 = new int[n];
    int[] dp2 = new int[n];
    dp1[0] = -prices[0];
    for (int i=1; i<prices.length; i++) {
        dp1[i] = Math.max(dp1[i-1], dp2[i-1]-prices[i]);
        dp2[i] = Math.max(dp2[i-1], dp1[i-1]+prices[i]-fee);
    }
    return dp2[n-1];
}
//376 摆动序列
public int wiggleMaxLength(int[] nums) {
    if (nums.length==0) return 0;
    int n = nums.length;
    int[] up = new int[n];
    int[] down = new int[n];
    up[0] = 1;
    down[0] = 1;

    for (int i=1; i<nums.length; i++) {
        if (nums[i]>nums[i-1]) {
            up[i] = down[i-1]+1;
            down[i] = down[i-1];
        } else if (nums[i] < nums[i-1]) {
            down[i] = up[i-1] + 1;
            up[i] = up[i-1];
        } else {
            down[i] = down[i-1];
            up[i] = down[i-1];
        }
    }
    return Math.max(down[n-1], up[n-1]);
}

//139 word break
public boolean wordBreak(String s, List<String> wordDict) {
    boolean[] f = new boolean[s.length+1];
    f[0] = true;
    for (int i=1; i<=s.length; i++) {
        for (int i=0; i<i; i++) {
            if (f[j] && wordDict.containsKey(s.substring(j, i))) {
                f[i] = true;
                break;
            }
        }
    }
    return f[s.length-1];
}

/**
 * 区间
 */
//583  两个字符串的删除操作
public int minDistance(String word1, String word2) {
    int m = word1.length, n = word2.length;
    int[][] dp = new int[m+1][n+1];
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            if (i==0) dp[i][j] = j;
            else if (j==0) dp[i][j] = i;
            else {
                if (word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+1;
                }
            }
        }
    }
    return dp[m][n];
}

//72 编辑距离
//375 猜数字游戏II
public int getMoneyAmount(int n) {
    int[][] table = new int[n+1][n+1];
    return dp[table, 1, n];
}
private int dp(int[][] t, int s, int e) {
    if (s>=e) return 0;
    if (t[s][e] != 0) return t[s][e];
    int res = Integer.MAX_VALUE;
    for (int x=s; x<=e; x++) {
        int tmp = x + Math.max(dp(t, s, x-1), dp(t, x+1, e));
        res = Math.min(res, tmp);
    } 
    t[s][e] = res;
    return res;
}

//718 最长重复子数组
public int findLength(int[] A, int[] B) {
    int m = A.length, n = B.length;
    int[][] dp = new int[m+1][n+1];
    int max = 0;
    for (int i=1; i<=m; i++) {
        for (int j=1; j<=n; j++) {
            if (A[i]==B[i]) {
                dp[i][j] = dp[i-1][j-1]+1;
                max = Math.max(max, dp[i][j]);
            } else {
                dp[i][j] = 0;
            }
        }
    }
    return max;
}

//712 两个字符串的最小ASCII删除和
public int minimumDeleteSum(String s1, String s2) {
    int m = s1.length, n = s2.length;
    int[][] dp = new int[m+1][n+1];
    for (int i=0; i<=m; i++) {
        for (int j=0; j<=n; j++) {
            if (i==0 && j==0) dp[i][j] = 0;
            else if (i==0) dp[i][j] = dp[i][j-1]+s2.charAt(j-1);
            else if (j==0) dp[i][j] = dp[i-1][j]+s1.charAt(i-1);
            else {
                if (s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1]+s2.charAt(j-1));
                }
            }
        }
    }
    return dp[m][n];
}

//1035  不相交的线
public int maxUncrossedLines(int[] A, int[] B) {
    int m = A.length, n = B.length;
    int[][] dp = new int[m+1][n+1];
    for (int i=0; i<=m; i++) {
        for (int j=0; j<=n; j++) {
            if (i==0 || j==0) dp[i][j] = 0;
            else {
                if (A[i-1]==B[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    return dp[m][n];
}


/*—————————————————————————————————————————————bit—————————————————————————————————————————————————————*/
//289 生命游戏
public void gameOfLife(int[][] board) {
    if (board==null || board.length==0 || board[0].length==0) return;
    int m = board.length, n = board[0].length;
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            int lives = neighbors(board, m, n, i, j);
            if (board[i][j]==1 && lives>=2 && lives<=3) {
                board[i][j] = 3;
            }
            if (board[i][j]==0 && lives==3) {
                board[i][j] = 2;
            }
        }
    }
    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            board[i][j] >>= 1;
        }
    }
}
private int neighbors(int[][] board, int m, int n, int i, int j) {
    int lives = 0;
    for (int x=Math.max(0, i-1); i<=Math.min(m-1, i+1); x++) {
        for (int y=Math.max(0, j-1); y<=Math.min(n-1, j+1), y++) {
            lives += board[x][y]&1;
        }
    }
    lives -= board[i][j]&1;
    return lives;
}

//318 最大单词长度乘积
public int maxProduct(String[] words) {
    if (words==null || words.length==0) return 0;
    int n = words.length;
    int[] value = new int[n];
    for (int i=0; i<n; i++) {
        String tmp = words[i];
        value[i] = 0;
        for (int j=0; j<tmp.length(); j++) {
            value[i] |= 1<<(tmp.charAt(i)-'a');
        }
    }
    int maxProduct = 0;
    for (int i=0; i<n; i++) {
        for (int j=i+1; j<n; j++) {
            if (value[i] & value[j]==0) {
                maxProduct = Math.max(maxProduct, words[i].length()*words[j].length());
            }
        }
    }
    return maxProduct;
}

//187 重复的DNA序列
public List<String> findRepeatedDnaSequences(String s) {
    Set<Integer> seen = new HashSet<>();
    Set<Integer> repeated = new HashSet<>();
    List<String> ans = new ArrayList<>();
    char[] map = new char[26];
    map['C'-'A'] = 1;
    map['G'-'A'] = 2;
    map['T'-'A'] = 3;
    for (int i=0; i<s.length()-9; i++) {
        int t = 0;
        for (int j=i; j<i+10; j++) {
            t <<= 2;
            t |= map[s.charAt[i]-'A'];
        }
        if (!seen.add(t) && repeated.add(t)) {
            ans.add(s.substring(i, i+10));
        }
    }
    return ans;
}

//389
public char findTheDifference(String s, String t) {
    char c = 0;
    for (int i=0; i<s.length(); i++) {
        c ^= s.charAt(i);
    }
    for (int i=0; i<t.length(); i++) {
        c ^= t.charAt(i);
    }
    return c;
}

//268 缺失数字
public int missingNumber(int[] nums) {
    int sum = nums.length;
    for (int i=0; i<nums.length; i++) {
        sum ^= nums[i];
        sum ^= i;
    }
    return sum;
}

//136 只出现一次的数字
public int singleNumber(int[] nums) {
    int res = 0;
    for (int i=0; i<nums.length; i++) {
        res = res ^ nums[i];
    }
    return res;
}

//137
public int singleNumber(int[] nums) {
    int ans = 0;
    for (int i=0; i<32; i++) {
        int count = 0;
        for (int num : nums) {
            count += (num>>i) & 1;
        }
        ans += (count%3)<<1;
    }
    return ans;
}

//26
public int[] singleNumber(int[] nums) {
    int diff = 0;
    for (int num : nums) {
        diff ^= num;
    }
    diff &= -diff;
    int[] ans = {0, 0};
    for (int num : nums) {
        if ((num & diff)!=0) {
            ans[0] ^= num;
        } else {
            ans[1] ^= num;
        }
    }
    return ans;
}

//461 汉明距离
public int hammingDistance(int x, int y) {
    int diff = x ^ y;
    int ans = 0;
    while (diff != 0) {
        ans++;
        diff &= diff-1;
    }
    return ans;
}

//476 数字的补数
public int findComplement(int num) {
    int c=0, x=num;
    while (x!=0) {
        x >>= 1;
        c = (c<<1) + 1;
    }
    return c^num;
}

//371 两数之和
public int getSum(int a, int b) {
    int sum, carry;
    sum = a ^ b;
    carray = (a&b)<<1;
    if (carray != 0) {
        getSum(sum, carray);
    }
    return sum;
}

//477 汉明距离总和
public int totalHammingDistance(int[] nums) {
    int res = 0;
    for (int i=0; i<31; i++) {
        int ones = 0;
        for (int x : nums) {
            if ((x>>i)&1==1) ones++;
        }
        res += ones * (nums.length-ones);
    }
    return res;
}

//342
public boolean isPowerOfFour(int num) {
    if (num<1) return false;
    int x = num&-num;
    if (x!=num) return false;
    while (x!=0) {
        if ((x&1)==1) return true;
        x >>= 2;
    }
    return false;
}

//231 交替位二进制数
public boolean hasAlternatingBits(int n) {
    int pre = n&1;
    while(n!=0) {
        if ((n&1)!=pre) return false;
        n>>=1;
        pre = pre==1 ? 0 : 1;
    }
    return true;
}

//401 二进制手表
public List<String> readBinaryWatch(int num) {
    List<String> ans = new ArrayList<>();
    for (int i=0; i<12) {
        for (int j=0; j<60; j++) {
            if (count(i)+count(j)==num) {
                ans.add(i+":"+(j<10?"0"+j : j));
            }
        }
    }
    return ans;
}
private int count(n) {
    int ans = 0;
    while(n!=0) {
        ans++;
        n &= n-1;
    }
    return ans;
}

//405 二进制转16进制
//397 整数替换
public int integerReplacement(int n) {
    int c = 0;
    while(n!=1) {
        if ((n&1)==0) n>>=1;
        else if (n==3 || ((n>>1)&1)==0) {
            --n;
        } else {
            ++n;
        }
        ++c;
    }
    return c;
}

//191 位1的个数
//762 二进制表示中质数个数置位
public int countPrimeSetBits(int L, int R) {
    HashSet<Integer> set = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19));
    int ans = 0;
    for (int i=L; i<=R; i++) {
        int s = 0;
        for (int k=i; k>0; k>>=1) s+= k&1;
        if (set.contains(s)) ans++;
    }
    return ans;
}
//190 颠倒二进制数
//393 utf8编码验证
//201 数字范围按位与
public int rangeBitwiseAnd(int m, int n) {
    int ans = 0;
    for (int i=0; 1<<i<=m; i++) {
        if ((m>>i & 1) == 1) {
            if ((~((1<<i)-1)&m)+(1<<i) > n) {
                res += 1<<i;
            }
        }
    }
    return ans;
}
//898 子数组按位或操作
public int subarrayBitwiseORs(int[] A) {
    HashSet<Integer> res=new HashSet<>(), cur = new HashSet<>(), cur2;
    for (Integer i : A) {
        cur2 = new HashSet<>();
        cur2.add(i);
        for (Integer j : cur) cur2.add(j|i);
        cur = cur2;
        res.addAll(cur2);
    }
    return res.size();
}
//338 比特位计数
public int[] countBits(int num) {
    int[] ans = new int[num+1];
    for (int i=1; i<=num; i++) {
        ans[i] = ans[i>>1] + (i&1);
    }
    return ans;
}
//29 两数相除
public int divide(int dividend, int divisor) {
    int ans=0, sign=1;
    if((dividend>0&&divisor<0) || (dividend<0&&divisor>0)) sign=-1;
    long dvd=Math.abs(dividend), dvs=Math.abs(divisor);
    while (dvd>=dvs) {
        long tmp = dvs, m=1;
        while((tmp<<1) <= dvd) {
            tmp<<=1;
            m<<1;
        }
        dvd-=tmp;
        ans+=m;
    }
    return sign*ans;
}
//421 数组中两个数的最异或值
public int findMaximumXOR(int[] nums) {
    if (nums==null || nums.length<=1) return 0;
    TrieTree tree = new TrieTree();
    for (int n : nums){
        tree.insert(n);
    }
    TrieNode cur = tree.root;
    while (cur.one == null || cur.zero == null) {
        cur = cur.zero!=null ? cur.zero : cur.one;
    }
    return maxHelper(cur.one, cur.zero);
}
private int maxHelper(TrieNode one, TrieNode zero) {
    if (one.isEnd && zero.isEnd) return one.val ^ zero.val;
    if (one.zero == null) {
        return maxHelper(one.one, zero.zero==null? zero.one : zero.zero);
    } else if (one.one == null) {
        return maxHelper(one.zero, zero.one==null? zero.zero, zero.one);
    } else if (zero.zero == null) {
        return maxHelper(zero.one, one.zero);
    } else if (zero.one == null) {
        return maxHelper(zero.zero, one.one);
    } else {
        return Math.max(maxHelper(one.one, zero.zero), maxHelper(one.zero, zero.one));
    }
}
class TrieNode{
    int val;
    boolean isEnd;
    TrieNode zero, one;
}
class TrieTree {
    TrieNode root;
    public TrieTree() { root=new TrieNode(); }
    public void insert(int num) {
        TrieNode cur = root;
        int j=1<<30;
        for (int i=0; i<31; i++) {
            int b = (j&num)==0 ? 0 : 1;
            if (b==0 && cur.zero==null) cur.zero = new TrieNode();
            if (b==1 && cur.one==null) cur.one = new TrieNode();
            cur = b==0 ? cur.zero : cur.one;
            j>>=1;
        }
        cur.isEnd = true;
        cur.val = num;
    }
}

