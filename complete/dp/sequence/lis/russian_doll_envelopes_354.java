package complete.dp.sequence.lis;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/29
 */
public class russian_doll_envelopes_354 {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length==0) return 0;
        Arrays.sort(envelopes, (a, b)-> {
            if (a[0]==b[0]) return b[1]-a[1];
            return a[0]-b[0];
        });
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i=1; i<envelopes.length; i++) {
            for (int j=i-1; j>=0; j--) {
                if (envelopes[i][1]>envelopes[j][1] && dp[j]+1>dp[i]) {
                    dp[i] = dp[j]+1;
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
