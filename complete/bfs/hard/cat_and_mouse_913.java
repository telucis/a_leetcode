package complete.bfs.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/23
 *
 * 猫和老鼠
 *
    两个玩家分别扮演猫（Cat）和老鼠（Mouse）在无向图上进行游戏，他们轮流行动。

    该图按下述规则给出：graph[a] 是所有结点 b 的列表，使得 ab 是图的一条边。

    老鼠从结点 1 开始并率先出发，猫从结点 2 开始且随后出发，在结点 0 处有一个洞。

    在每个玩家的回合中，他们必须沿着与他们所在位置相吻合的图的一条边移动。例如，如果老鼠位于结点 1，那么它只能移动到 graph[1] 中的（任何）结点去。

    此外，猫无法移动到洞（结点 0）里。

    然后，游戏在出现以下三种情形之一时结束：

    如果猫和老鼠占据相同的结点，猫获胜。
    如果老鼠躲入洞里，老鼠获胜。
    如果某一位置重复出现（即，玩家们的位置和移动顺序都与上一个回合相同），游戏平局。
    给定 graph，并假设两个玩家都以最佳状态参与游戏，如果老鼠获胜，则返回 1；如果猫获胜，则返回 2；如果平局，则返回 0。



    示例：

    输入：[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
    输出：0
    解释：
    4---3---1
    |   |
    2---5
     \ /
      0


    提示：

    3 <= graph.length <= 200
    保证 graph[1] 非空。
    保证 graph[2] 包含非零元素。

 */
public class cat_and_mouse_913 {
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        // cat位置 mouse位置 mouse/cat先手 猫赢/鼠赢/平局
        int[][][] color = new int[n][n][2];
        // cat位置 mouse位置 mouse/cat先手 出度
        int[][][] outdegree = new int[n][n][2];
        for (int i=0; i<n; i++) { //cat
            for (int j=0; j<n; j++) {  //mouse
                outdegree[i][j][0] = graph[j].length;
                outdegree[i][j][1] = graph[i].length;
                for (int k:graph[i]) {
                    if (k==0) {
                        outdegree[i][j][1]--;
                        break;
                    }
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int k=1; k<n; k++) {
            for (int m=0; m<2; m++) {
                color[k][0][m] = 1;
                q.offer(new int[]{k, 0, m, 1});
                color[k][k][m] = 2;
                q.offer(new int[]{k, k, m, 2});
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cat=cur[0], mouse=cur[1], mouseMove=cur[2], c=cur[3];
            if (cat==2 && mouse==1 && mouseMove==0) {
                return c;
            }
            int prevMouseMove = 1-mouseMove;
            for (int prev : graph[prevMouseMove==1 ? cat : mouse]) {
                int prevCat = prevMouseMove==1 ? prev : cat;
                int prevMouse = prevMouseMove==0 ?  prev : mouse;
                if (prevCat==0) continue;
                if (color[prevCat][prevMouse][prevMouseMove]>0) continue;
                if (prevMouseMove==1 && c==2 ||
                    prevMouseMove==0 && c==1 ||
                    --outdegree[prevCat][prevMouse][prevMouseMove]==0) {
                    color[prevCat][prevMouse][prevMouseMove]=c;
                    q.offer(new int[]{prevCat, prevMouse, prevMouseMove, c});
                }
            }
        }
        return color[2][1][0];
    }
}
