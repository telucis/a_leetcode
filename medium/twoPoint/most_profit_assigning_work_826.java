package medium.twoPoint;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by telucis on 2019/5/11.
 *
 * 安排工作以达到最大收益
 *
     有一些工作：difficulty[i] 表示第i个工作的难度，profit[i]表示第i个工作的收益。

     现在我们有一些工人。worker[i]是第i个工人的能力，即该工人只能完成难度小于等于worker[i]的工作。

     每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。

     举个例子，如果3个工人都尝试完成一份报酬为1的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。

     我们能得到的最大收益是多少？

     示例：

     输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
     输出: 100
     解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
     提示:

     1 <= difficulty.length = profit.length <= 10000
     1 <= worker.length <= 10000
     difficulty[i], profit[i], worker[i]  的范围是 [1, 10^5]

 */
public class most_profit_assigning_work_826 {

    /**
     * sort - twoPointer
     */
    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        List<int[]> jobs = new ArrayList<>();
        int N = profit.length, res = 0, i = 0, maxp = 0;
        for (int j = 0; j < N; ++j) jobs.add(new int[]{difficulty[j], profit[j]});
        Collections.sort(jobs, (a, b)->a[0]-b[0]);
        Arrays.sort(worker);
        for (int ability : worker) {
            while (i < N && ability >= jobs.get(i)[0])
                maxp = Math.max(jobs.get(i++)[1], maxp);
            res += maxp;
        }
        return res;
    }

    /**
     * heap
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n=difficulty.length, ans=0, max=0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0; i<n; i++) {
            map.put(difficulty[i], Math.max(map.getOrDefault(difficulty[i], 0), profit[i]));
        }
        for (Integer key : map.keySet()) {
            max = Math.max(max, map.get(key));
            map.put(key, max);
        }
        for (int w : worker) {
            if (map.containsKey(w)) {
                ans += map.get(w);
            } else {
                Map.Entry<Integer, Integer> e = map.floorEntry(w);
                if (e != null) {
                    ans += e.getValue();
                }
            }
        }
        return ans;
    }
}
