package complete.dp.coord.array;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/18
 *
 * 骑士拨号器
 *
    国际象棋中的骑士可以按下图所示进行移动：

    .


    这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。

    每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。

    你能用这种方式拨出多少个不同的号码？

    因为答案可能很大，所以输出答案模 10^9 + 7。



    示例 1：

    输入：1
    输出：10
    示例 2：

    输入：2
    输出：20
    示例 3：

    输入：3
    输出：46


    提示：

    1 <= N <= 5000

 */
public class knight_dialer_935 {

    public int knightDialer(int N) {
        int mod = 1000000007;
        long[] ans = new long[10];
        Arrays.fill(ans,1 );
        while (N-->1) {
            long[] n = new long[10];
            n[0]=(ans[4]+ans[6])%mod;
            n[1]=(ans[6]+ans[8])%mod;
            n[2]=(ans[7]+ans[9])%mod;
            n[3]=(ans[4]+ans[8])%mod;
            n[4]=(ans[0]+ans[3]+ans[9])%mod;
            n[5]=0;
            n[6]=(ans[0]+ans[1]+ans[7])%mod;
            n[7]=(ans[2]+ans[6])%mod;
            n[8]=(ans[1]+ans[3])%mod;
            n[9]=(ans[2]+ans[4])%mod;
            ans=n;
        }
        long res=0;
        for (long i:ans) {
            res = (res+i)%1000000007;
        }
        return (int)res;
    }

    /**
         In fact, we recursively did pow operation.
         This can be optimised to O(log) time.

         Construct a 10 * 10 transformation matrix M.
         M[i][j] = 1 if i and j is connnected.

         if N = 1, return 10.
         if N > 1, return sum of [1,1,1,1,1,1,1,1,1,1] * M ^ (N - 1)

         The power of matrix reveals the number of walks in an undirected graph.
         Find more details on this link provide by @shankark:
         https://math.stackexchange.com/questions/1890620/finding-path-lengths-by-the-power-of-adjacency-matrix-of-an-undirected-graph

         def knightDialer(self, N):
         mod = 10**9 + 7
         if N == 1: return 10
         M = np.matrix([[0, 0, 0, 0, 1, 0, 1, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 1, 0, 1, 0],
         [0, 0, 0, 0, 0, 0, 0, 1, 0, 1],
         [0, 0, 0, 0, 1, 0, 0, 0, 1, 0],
         [1, 0, 0, 1, 0, 0, 0, 0, 0, 1],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [1, 1, 0, 0, 0, 0, 0, 1, 0, 0],
         [0, 0, 1, 0, 0, 0, 1, 0, 0, 0],
         [0, 1, 0, 1, 0, 0, 0, 0, 0, 0],
         [0, 0, 1, 0, 1, 0, 0, 0, 0, 0]])
         res, N = 1, N - 1
         while N:
         if N % 2: res = res * M % mod
         M = M * M % mod
         N /= 2
         return int(np.sum(res)) % mod

     */
}
