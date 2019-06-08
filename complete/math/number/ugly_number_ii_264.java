package complete.math.number;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 丑数 II
 *
    编写一个程序，找出第 n 个丑数。

    丑数就是只包含质因数 2, 3, 5 的正整数。

    示例:

    输入: n = 10
    输出: 12
    解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
    说明:

    1 是丑数。
    n 不超过1690。

 */
public class ugly_number_ii_264 {
    /**
     * heap
     */
    public int nthUglyNumber(int n) {
        if (n==1) return 1;
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(1L);
        for (long i=1; i<n; i++) {
            long tmp = q.poll();
            while (!q.isEmpty() && q.peek()==tmp) tmp = q.poll();
            q.add(tmp*2);
            q.add(tmp*3);
            q.add(tmp*5);
        }
        return q.poll().intValue();
    }

    /**
     * dp
     */
    public int nthUglyNumber2(int n) {
        if (n==1) return 1;
        int t2=0, t3=0, t5=0;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i=1; i<n; i++) {
            ans[i] = Math.min(ans[t2]*2, Math.min(ans[t3]*3, ans[t5]*5));
            if (ans[i]==ans[t2]*2) t2++;
            if (ans[i]==ans[t3]*3) t3++;
            if (ans[i]==ans[t5]*5) t5++;
        }
        return ans[n-1];
    }
}
