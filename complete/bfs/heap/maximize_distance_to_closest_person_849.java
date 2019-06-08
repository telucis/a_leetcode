package complete.bfs.heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 到最近的人的最大距离
 *
    在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。

    至少有一个空座位，且至少有一人坐在座位上。

    亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。

    返回他到离他最近的人的最大距离。

    示例 1：

    输入：[1,0,0,0,1,0,1]
    输出：2
    解释：
    如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
    如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
    因此，他到离他最近的人的最大距离是 2 。
    示例 2：

    输入：[1,0,0,0]
    输出：3
    解释：
    如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
    这是可能的最大距离，所以答案是 3 。
    提示：

    1 <= seats.length <= 20000
    seats 中只含有 0 和 1，至少有一个 0，且至少有一个 1。

 */
public class maximize_distance_to_closest_person_849 {

    public int maxDistToClosest(int[] seats) {
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[seats.length];
        for (int i=0; i<seats.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            if (seats[i] == 1) {
                queue.offer(i);
                distance[i] = 0;
            }
        }
        int count = 0;
        int[] move = new int[]{-1, 1};
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int indexCur = queue.poll();
                for (int j=0; j<2; j++) {
                    int index = indexCur + move[j];
                    if (index>=0 && index<seats.length && distance[index]==Integer.MAX_VALUE) {
                        distance[index] = count;
                        queue.offer(index);
                    }
                }

            }
        }
        return count-1;
    }
}
