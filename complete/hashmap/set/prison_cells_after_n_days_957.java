package complete.hashmap;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by telucis on 2019/5/13.
 *
 *  N 天后的牢房
 *
     8 间牢房排成一排，每间牢房不是有人住就是空着。

     每天，无论牢房是被占用或空置，都会根据以下规则进行更改：

     如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
     否则，它就会被空置。
     （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）

     我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。

     根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。



     示例 1：

     输入：cells = [0,1,0,1,1,0,0,1], N = 7
     输出：[0,0,1,1,0,0,0,0]
     解释：
     下表概述了监狱每天的状况：
     Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
     Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
     Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
     Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
     Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
     Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
     Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
     Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

     示例 2：

     输入：cells = [1,0,0,1,0,0,1,0], N = 1000000000
     输出：[0,0,1,1,1,1,1,0]


     提示：

     cells.length == 8
     cells[i] 的值为 0 或 1
     1 <= N <= 10^9

 */
public class prison_cells_after_n_days_957 {

    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells==null || cells.length==0 || N<0) return null;
        boolean hasCycle=false;
        int cycle=0;
        HashSet<String> set = new HashSet<>();
        for (int i=0; i<N; i++) {
            int[] next = nextDay(cells);
            String str = Arrays.toString(next);
            if (!set.contains(str)) {
                set.add(str);
                cycle++;
            } else {
                hasCycle=true;
                break;
            }
            cells=next;
        }
        if (hasCycle) {
            N %= cycle;
            for (int i=0; i<N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }
    private int[] nextDay(int[] cells) {
        int[] tmp = new int[cells.length];
        for (int i=1; i<cells.length-1; i++) {
            tmp[i] = cells[i-1]==cells[i+1] ? 1 : 0;
        }
        return tmp;
    }
}
