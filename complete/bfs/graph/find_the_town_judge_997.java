package complete.bfs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/26
 *
 * 找到小镇的法官
 *
    在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。

    如果小镇的法官真的存在，那么：

    小镇的法官不相信任何人。
    每个人（除了小镇法官外）都信任小镇的法官。
    只有一个人同时满足属性 1 和属性 2 。
    给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。

    如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。



    示例 1：

    输入：N = 2, trust = [[1,2]]
    输出：2
    示例 2：

    输入：N = 3, trust = [[1,3],[2,3]]
    输出：3
    示例 3：

    输入：N = 3, trust = [[1,3],[2,3],[3,1]]
    输出：-1
    示例 4：

    输入：N = 3, trust = [[1,2],[2,3]]
    输出：-1
    示例 5：

    输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
    输出：3


    提示：

    1 <= N <= 1000
    trust.length <= 10000
    trust[i] 是完全不同的
    trust[i][0] != trust[i][1]
    1 <= trust[i][0], trust[i][1] <= N

 */
public class find_the_town_judge_997 {

    public int findJudge(int N, int[][] trust) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int max = 0;
        int judge = 1;
        for (int i=0; i<trust.length; i++) {
            int truster = trust[i][0];
            int trusted = trust[i][1];
            if (map.containsKey(truster)) {
                map.get(trusted).add(trusted);
            } else {
                map.put(trusted, new ArrayList<Integer>(){{add(truster);}});
            }
            if (map.get(trusted).size() > max) {
                max = map.get(trusted).size();
                judge = trusted;
            }
        }
        if (max != N-1) {
            return -1;
        }
        for (int key : map.keySet()) {
            if (key != judge && map.get(key).contains(judge)) {
                return -1;
            }
        }
        return judge;
    }

    public int findJudge2(int N, int[][] trust) {
        int[][] people = new int[N][2];
        for (int[] person : trust) {
            int out = person[0];
            int in = person[1];
            people[out-1][0]++;
            people[in-1][1]++;
        }
        for (int i=0; i<N; i++) {
            if (people[i][0] == 0 && people[i][1]==N-1) {
                return i+1;
            }
        }
        return -1;
    }
}
