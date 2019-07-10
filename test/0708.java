//756
public boolean pyramidTransition(String bottom, List<String> allowed) {
    Map<String, Set<String>> map = new HashMap<>();
    for (String str : allowed) {
        map.putIfAbsent(str.substring(0, 2), new HashSet<>());
        map.get(str.substring(0, 2)).add(str.substring(2));
    }
    return dfs(bottom, map);
}
private boolean dfs(String bottom, Map<String, Set<String>> map) {
    if (bottom.length()==1) return true;
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<bottom.length(); i++) {
        String key = bottom.substring(1, i+2);
        if (!map.containsKey(key)) return false;
    }
    List<String> ls = new ArrayList<>();
    getList(bottom, 0, new StringBuilder(), ls, map);
    for (String b : ls) {
        if (dfs(b, map)) return true;
    }
    return false;
}
private void getList(String bottom, int idx, StringBuilder sb, List<String> ls, Map<String, Set<String>> map) {
    if (idx==bottom.length()-1) {
        ls.add(sb.toString());
        return;
    }
    for (String s : map.get(bottom.substring(idx, idx+2))) {
        sb.append(s);
        getList(bottom, idx+1, sb, ls, map);
        sb.deleteCharAt(sb.length()-1);
    }
}

/**
 * dfs-divideConquer
 */
//932
int[] ans;
public int[] beautifulArray(int N) {
    ArrayList<Integer> ans = new ArrayList<>();
    ans.add(1);
    while (ans.size() < N) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i : ans) if (i*2-1 <= N) tmp.add(i*2-1);
        for (int i : ans) if (i*2 <= N) tmp.add(i*2);
        ans = tmp;
    }
    return ans.stream().mapToInt(i->i).toArray();
}

//241
public List<Integer> diffWaysToCompute(String input) {
    List<Integer> ans = new ArrayList<>();
    for (int i=0; i<input.length(); i++) {
        if (input.charAt(i)=='-' ||
            input.charAt(i)=='+' ||
            input.charAt(i)=='*') {
            List<Integer> p1Ret = diffWaysToCompute(input.substring(0, i));
            List<Integer> p2Ret = diffWaysToCompute(input.substring(i+1, input.length()));
            for (int p1 : p1Ret) {
                for (int p2 : p2Ret) {
                    int c = 0;
                    switch(input.charAt(i)) {
                        case "+" : 
                            c=p1+p2;
                            break;
                        case "-" :
                            c=p1-p2;
                            break;
                        case "*" :
                            c=p1*p2;
                            break;
                    }
                    ans.add(c);
                }
            }
        }
    }
    if (ans.size==0) ans.add(Integer.valueOf(input));
    return ans;
}

//22
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    generate(res, "", n, 0, 0);
    return res;
}
private void generate(List<String> res, String ans, int n, int count1, int count2) {
    if (count1==n && count2==n) {
        res.add(ans);
    }
    if (count1>n || count2>n) {
        return;
    }
    if (count1>=count2) {
        generate(res, ans+"(", n, count1+1, count2);
        generate(res, ans+")", n, count1, count2+1);
    }
}

//89
public List<Integer> grayCode(int n) {
    List<Integer> ans = new ArrayList<>();
    if (n<0) return ans;
    if (n==0) {
        ans.add(0);
        return ans;
    }
    List<Integer> tmp = grayCode(n-1);
    List<Integer> result = new ArrayList<>(tmp);
    int addNumber = 1<<(n-1);
    for (int i=tmp.size()-1; i>=0; i--) {
        result.add(tmp.get(i)+addNumber);
    }
    return result;
}
public List<Integer> grayCode(int n) {
    List<Integer> list = new ArrayList<>();
    for (int i=0; i<Math.pow(2, n), i++) {
        list.add(i);
    }
    return dfs(list, new LinkedList<>(), Math.pow(2, n));
}
private List<Integer> dfs(List<Integer> list, LinkedList<Integer> tmp, int n) {
    if (tmp.size==0) return tmp;
    for (int i=0; i<list.size; i++) {
        int num = list.get(i);
        if (tmp.isEmpty() || judge(tmp.getLast(), num)) {
            tmp.add(num);
            list.remove(i);
            List<Integer> res = dfs(list, tmp, n);
            if (res!=null) return res;
            tmp.removeLast();
            list.add(i, num);
        }
    }
    return null;
}
private boolean judge(int a, int b) {
    int c = a^b, count = 0;
    while (c>0) {
        if ((c&1)==1) count++;
        c>>=1;
        if (count>1) return false;
    }
    return count==1;
}

//494
public int findTargetSumWays(int[] nums, int S) {
    dfs(nums, 0, S);
    return ans;
}
int ans;
private void dfs(int[] nums, int start, int S) {
    if (start==nums.length) {
        if (S==0) ans++;
        return;
    }
    dfs(nums, start+1, S+nums[start]);
    dfs(nums, start+1, S-nums[start]);
}

//576
private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
private int mod = 1000000007;
public int findPaths(int m, int n, int N, int i, int j) {
    long[][][] memo = new long[m][n][N+1];
    for (int ii=0; ii<m; ii++) {
        for (int jj=0; jj<n; jj++) {
            for (int kk=0; kk<N+1; kk++) {
                memo[ii][jj][kk] = -1;
            }
        }
    }
    return dfs(m, n, i, j, memo);
}
private long dfs(int m, int n, int i, int j, int N, int[][][] memo) {
    if (i<0 || i>=m || j<0 || j>=n) return 1;
    if (N==0) return 0;
    if (memo[i][j][N]!=-1) return memo[i][j][N];
    memo[i][j][N] = 0;
    for (int[] dir : dirs) {
        int x = dir[0]+i;
        int y = dir[1]+j;
        memo[i][j][N] = (memo[i][j][N] + dfs(m, n, x, y, N-1, memo)%mod)%mod;
    }
    return memo[i][j][N];
}

//473
public boolean makesquare(int[] nums) {
    if (nums==null || nums.length<4) return false;
    int sum = 0;
    for(int i : nums) sum+=i;
    if (sum%4 != 0) return false;
    return dfs(nums, new int[4], 0, sum/4);
}
private boolean dfs(int[] nums, int[] sum, int index, int target) {
    if (index==nums.length) {
        if (sum[0]==target && sum[1]==target && sum[2]==target) return true;
        return false;
    }
    for (int i=0; i<4; i++) {
        if (sum[i]+nums[index]>target) continue;
        sum[i] += nums[index];
        if (dfs(nums, sum, index+1, target)) return true;
        sum[i] -= nums[index];
    }
    return false;
}

//698
public boolean canPartitionKSubsets(int[] nums, int k) {
    if (nums==null || nums.length<k) return false;
    int n = nums.length, sum=0;
    for (int i : nums) sum+=i;
    if (sum%k!=0) return false;
    return dfs(nums, k, sum/k, new int[n], 0, 0);
}
private boolean dfs(int[] nums, int k, int target, int[] used, int cur_index, int cur_sum) {
    if (k==1) return true;
    if (target==cur_sum) return dfs(nums, k-1, target, used, 0, 0);
    for (int i=cur_index; i<nums.length; i++) {
        if (used[i]==0) {
            used[i]=1;
            dfs(nums, k, target, used, i+1, cur_sum+nums[i]);
            used[i]=0;
        }
    }
}

/**
 * dfs-traversal
 */
//306
public boolean isAdditiveNumber(String num) {
    int n = num.length();
    for (int i=1; i<=n/2; i++) {
        if (num.charAt(0)=='0' && i>1) return false;
        BigInteger x1 = new BigInteger(num.substring(0, i));
        for (int j=1; Math.max(j, i)<=n-i-j; j++) {
            if (num.charAt(i)=='0' && j>1) break;
            BigInteger x2 = new BigInteger(nums.substring(i, i+j));
            if (isValid(x1, x2, i+j, num)) return true;
        }
    }
    return false;
}
private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
    x2 = x2.add(x1);
    x1 = x2.subtract(x1);
    String sum = x2.toString();
    return num.startWith(sum, start) && isValid(x1, x2, start+sum.length(), num); 
}

//1034
public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
    dfs(grid, r0, c0, grid[r0][c0]);
    for (int i=0; i<grid.length; i++) {
        for (int j=0; j<grid[0].length; j++) {
            grid[i][j] = grid[i][j]<0 ? color : grid[i][j];
        }
    }
    return grid;
}
private void dfs(int[][] grid, int r, int c, int color) {
    if (r<0 || r>=grid.length || c<0 || c>=grid.length || grid[r][c]!=color) return;
    grid[r][c] = -color;
    dfs(grid, r+1, c, color);
    dfs(grid, r-1, c, color);
    dfs(grid, r, c+1, color);
    dfs(grid, r, c-1, color);
    if (r>0 && r<grid.length-1 && c>0 && c<grid[0].length &&
        color==Math.abs(grid[r-1][c]) &&
        color==Math.abs(grid[r+1][c]) &&
        color==Math.abs(grid[r][c+1]) &&
        color==Math.abs(grid[r][c-1])) {
        grid[r][c] = c1;
    }
}

//841
public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int n = rooms.size();
    boolean[] state = new boolean[n];
    dfs(rooms, state, 0);
    for (int i=0; i<n; i++) {
        if (!static[i]) return false;
    }
    return true;
}
private void dfs(List<List<Integer>> rooms, boolean[] state, int index) {
    state[index] = true;
    for (int i=0; i<rooms.get(index).length; i++) {
        if (!state[rooms.get(index).get(i)]) {
            dfs(rooms, state, rooms.get(index).get(i));
        }
    }
}

//386
public List<Integer> lexicalOrder(int n) {
    List<Integer> ans = new ArrayList<>();
    for (int i=1; i<10; i++) {
        dfs(i, n, ans);
    }
    return ans;
}
private void dfs(int num, int n, List<Integer> ans) {
    if (num>n) return;
    ans.add(num);
    for (int i=0; i<10; i++) {
        dfs(num*10+i, n, ans);
    }
}

//385. mini parser
public NestedInteger deserialize(String s) {
    NestedInteger ret = new NestedInteger();
    if (s==null || s.length()==0) return ret;
    if (s.charAt(0)!='[') {
        ret.setInteger(Integer.parseInt(s));
    } else if (s.length()>2) {
        int start=1, count=0;
        for (int i=1; i<s.length(); i++) {
            char c = s.charAt(i);
            if (count==0 && (c==','||i==s.length()-1)) {
                ret.add(deserialize(s.substring(start, i)));
                start = i+1;
            } else if (c=='[') {
                count++;
            } else if (c==']') {
                count--;
            }
        }
    }
    return ret;
}

//967 连续差相同的数字
public int[] numsSameConsecDiff(int N, int K) {
    List<Integer> ans = new ArrayList<>();
    for (int i=(N==0? 0 : 1); i<10; i++) {

    }
    return ans.stream().mapToInt(a->a).toArray();
}
private void dfs(List<Integer> ans, int N, int K, int tmp) {
    if (N==0) {
        ans.add(tmp);
        return;
    }
    for (int i=0; i<10; i++) {
        if (k==Math.abs(tmp%10-i)) {
            dfs(ans, N-1, K, tmp*10+i);
        }
    }
}

//332 重新安排行程
//欧拉路径
Map<String, PriorityQueue<String> flights;
LinkedList<String> path;
public List<String> findItinerary(List<List<String>> tickets) {
    flights = new HashMap<>();
    path = new LinkedList<>();
    for (List<String> t : tickets) {
        flights.putIfAbsent(t.get(0), new PriorityQueue<>());
        flights.get(t.get(0)).add(t.get(1));
    }
    dfs("JFK");
    return path;
}
private void dfs(String departure) {
    PriorityQueue<String> arrivals = flights.get(departure);
    while (arrivals!=null && !arrivals.isEmpty()) {
        dfs(arrivals.poll());
    }
    path.addFirst(departure);
}

//638 大礼包
public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return helper(price, needs, special, 0);
}
private int helper(List<Integer> price, List<Integer> needs, List<List<Integer>> special, int index) {
    int local_min = directPurchase(price, needs);
    for (int i=index; i<special.size(); i++) {
        List<Integer> offer = special.get(i);
        List<Integer> tmp = new ArrayList<>();
        for (int j=0; j<needs.size(); j++) {
            if (needs.get(j) < offer.get(j)) {
                tmp = null;
                break;
            }
            tmp.add(needs.get(j)-offer.get(j));
        }
        if (tmp!=null) {
            local_min = Math.min(local_min, offer.get(offer.size()-1)+helper(price, special, tmp, i));
        }
    }
    return local_min;
}
private int directPurchase(List<Integer> price, List<String> needs) {
    int total = 0;
    for (int i=0; i<needs.size(); i++) {
        total += price.get(i) * needs.get(i);
    }
    return total;
}

//842 将数组拆分成斐波拉契数列
public List<Integer> splitIntoFibonacci(String s) {
    List<Integer> ans = new ArrayList<>();
    helper(s, ans, 0);
    return ans;
}
private boolean helper(String s, List<Integer> ans, int idx) {
    if (idx==s.length() && ans.size()>=3) return true;
    int size = ans.size();
    for (int i=idx; i<s.length(); i++) {
        if (s.charAt(idx)=='0' && i>idx) break;
        long num = Long.parseLong(s.substring(idx, i+1));
        if (num > Integer.MAX_VALUE) break;
        if (size>2 && num > ans.get(size-1)+ans.get(size-2)) {
            break;
        }
        if (size<=1 || num==ans.get(size-1)+ans.get(size-2)) {
            ans.add((int)num);
            if (helper(s, ans, i+1)) return true;
            ans.remove(ans.size()-1);
        }
    }
    return false;
}


//79
public boolean exist(char[][] board, String word) {
    char[] w = word.toCharArray();
    for (int i=0; i<board.length; i++) {
        for (int j=0; j<board[0].length; j++) {
            if (exist(board, i, j, w, 0)) return true;
        }
    }
    return false;
}
private boolean exist(char[][] board, int x, int y, char[] w, int i) {
    if (i==w.length) return true;
    if (x<0 || y<0 || x>=board.length || y>=board[0].length) return false;
    if (w[i] != board[x][y]) return false;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    board[x][y] ^= 256;
    boolean res = false;
    for (int k=0; k<4; k++) {
        if (exist(board, x+dirs[k][0], y+dirs[k][1], w, i+1)) {
            res = true;
            break;
        }
    }
    board[x][y] ^= 256;
    return res;
}








