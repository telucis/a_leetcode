package complete.dfs.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author karl.wy
 * @date 2019/05/25
 *
 * 自由之路
 *
    视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。

    给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。

    最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。

    旋转 ring 拼出 key 字符 key[i] 的阶段中：

    您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
    如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
    示例：





    输入: ring = "godding", key = "gd"
    输出: 4
    解释:
    对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
    对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
    当然, 我们还需要1步进行拼写。
    因此最终的输出是 4。
    提示：

    ring 和 key 的字符串长度取值范围均为 1 至 100；
    两个字符串中都只有小写字符，并且均可能存在重复字符；
    字符串 key 一定可以由字符串 ring 旋转拼出。

 */
public class freedom_trail_514 {

    /**
     * dfs + memoization
     */
    Map<String, Map<Integer, Integer>> memo;
    public int findRotateSteps(String ring, String key) {
        memo = new HashMap<>();
        return dfs(ring, key, 0);
    }
    private int dfs(String ring, String key, int i) {
        if (i==key.length()) return 0;
        int res=0;
        char ch = key.charAt(i);
        if (memo.containsKey(ring) && memo.get(ring).containsKey(i)) return memo.get(ring).get(i);
        int f = ring.indexOf(ch);
        int b = findBackPos(ring, ch);
        int forward = 1+f+dfs(ring.substring(f)+ring.substring(0,f),key, i+1);
        int back = 1+ring.length()-b+dfs(ring.substring(b)+ring.substring(0,b), key, i+1);
        res = Math.min(forward, back);
        Map<Integer, Integer> ans = memo.getOrDefault(ring, new HashMap<>());
        ans.put(i, res);
        memo.put(ring, ans);
        return res;
    }
    private int findBackPos(String ring, char ch) {
        if (ring.charAt(0)==ch) return 0;
        for (int i=ring.length()-1; i>0; i--) {
            if (ring.charAt(i)==ch) return i;
        }
        return 0;
    }


    /**
     * bfs
     */
    public int findRotateSteps2(String ring, String key) {
        StringBuilder sb = new StringBuilder();
        Queue<Ring> q = new LinkedList<>();
        int step=0;
        q.offer(new Ring(new StringBuilder(ring), 0, false));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Ring r = q.poll();
                if (r.sb.charAt(0)==key.charAt(r.index) &&
                    r.index==key.length()-1 &&
                    r.button) return step;
                if (r.sb.charAt(0)==key.charAt(r.index)) {
                    if (r.button) {
                        if (key.charAt(r.index+1)==r.sb.charAt(0)) {
                            r.index++;
                            q.offer(r);
                        } else {
                            rotateRing(q, r, r.index+1);
                        }
                    } else {
                        r.button=true;
                        q.offer(r);
                    }
                }
                else {
                    rotateRing(q, r, r.index);
                }
            }
            step++;
        }
        return step;
    }
    private void rotateRing(Queue<Ring> q, Ring r, int index) {
        StringBuilder sb1 = new StringBuilder(r.sb);
        StringBuilder sb2 = new StringBuilder(r.sb);
        sb1.append(sb1.charAt(0));
        sb1.deleteCharAt(0);
        sb2.insert(0, sb2.charAt(sb2.length()-1));
        sb2.deleteCharAt(sb2.length()-1);
        q.offer(new Ring(sb1, index, false));
        q.offer(new Ring(sb2, index, false));
    }
    private class Ring {
        public StringBuilder sb;
        public int index;
        public boolean button;

        public Ring(StringBuilder sb, int index, boolean button) {
            this.sb = sb;
            this.index = index;
            this.button = button;
        }
    }
}
