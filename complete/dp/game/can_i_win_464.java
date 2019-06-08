package complete.dp.game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/19
 *
 * 我能赢吗
 *
    在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。

    如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？

    例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。

    给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？

    你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。

    示例：

    输入：
    maxChoosableInteger = 10
    desiredTotal = 11

    输出：
    false

    解释：
    无论第一个玩家选择哪个整数，他都会失败。
    第一个玩家可以选择从 1 到 10 的整数。
    如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
    第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
    同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。

 */
public class can_i_win_464 {

    /**
     * dp up-bottom
     * fun: boolean helper(int desiredTotal) 先手拿到desiredTotal是否赢
     * core: !helper(desiredTotal-i)
     * state: map<format(used[]), boolean> 
     */
    Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger+1)*maxChoosableInteger/2;
        if (sum < desiredTotal) return false;
        if (desiredTotal<=0) return true;

        map = new HashMap<>();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }
    private boolean helper(int desiredTotal) {
        if (desiredTotal<=0) return false;
        int key = format(used);
        if (!map.containsKey(key)) {
            for (int i=1; i<used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    if (!helper(desiredTotal-i)) {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
    private  int format(boolean[] used) {
        int num = 0;
        for (boolean b : used) {
            num<<=1;
            if (b) num |= 1;
        }
        return num;
    }
}
