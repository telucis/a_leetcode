package medium.twoPoint.sweepLine;

import java.util.*;

/**
 * Created by telucis on 2019/5/12.
 *
 * 砖墙
 *
     你的面前有一堵方形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。

     砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。

     如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。

     你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。



     示例：

     输入: [[1,2,2,1],
     [3,1,2],
     [1,3,2],
     [2,4],
     [3,1,2],
     [1,3,1,1]]

     输出: 2

     解释:



     提示：

     每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
     每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过 20,000。

 */
public class brick_wall_554 {
    /**
     * sweepLine
     */
    public int leastBricks(List<List<Integer>> wall) {
        List<Node> list = new ArrayList<>();
        int count = 0, ans=wall.size(), len=0;
        for (Integer i : wall.get(0)) {
            len+=i;
        }
        for (List<Integer> brick : wall) {
            int index=0;
            for (Integer i : brick) {
                list.add(new Node(index, true));
                list.add(new Node(index+i, false));
                index += i;
            }
        }
        Collections.sort(list, (a, b)->{
            if (a.val==b.val && a.isStart!=b.isStart) {
                return !a.isStart ? -1 : 1;
            }
            return a.val-b.val;
        });
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).isStart) {
                count++;
            } else {
                count--;
            }
            if (list.get(i).val!=0 && list.get(i).val!=len) {
                ans = Math.min(ans, count);
            }
        }
        return ans;
    }
    class Node {
        public int val;
        public boolean isStart;

        public Node(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
    }

    /**
     * hashmap
     */
    public int leastBricks2(List<List<Integer>> wall) {
        if (wall.size()==0) return 0;
        int count=0;
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int length=0;
            for (int i=0; i<list.size(); i++) {
                length += list.get(i);
                map.put(length, map.getOrDefault(length, 0)+1);
                count = Math.max(count, map.get(length));
            }
        }
        return wall.size()-count;
    }
}
