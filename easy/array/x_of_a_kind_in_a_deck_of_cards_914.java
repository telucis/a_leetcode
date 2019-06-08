package easy.array;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 卡牌分组
 *
    给定一副牌，每张牌上都写着一个整数。

    此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：

    每组都有 X 张牌。
    组内所有的牌上都写着相同的整数。
    仅当你可选的 X >= 2 时返回 true。



    示例 1：

    输入：[1,2,3,4,4,3,2,1]
    输出：true
    解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
    示例 2：

    输入：[1,1,1,2,2,2,3,3]
    输出：false
    解释：没有满足要求的分组。
    示例 3：

    输入：[1]
    输出：false
    解释：没有满足要求的分组。
    示例 4：

    输入：[1,1]
    输出：true
    解释：可行的分组是 [1,1]
    示例 5：

    输入：[1,1,2,2,2,2]
    输出：true
    解释：可行的分组是 [1,1]，[2,2]，[2,2]

    提示：

    1 <= deck.length <= 10000
    0 <= deck[i] < 10000
 */
public class x_of_a_kind_in_a_deck_of_cards_914 {

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length<=1) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<deck.length; i++) {
            if (map.containsKey(deck[i])) {
                map.put(deck[i], map.get(deck[i])+1);
            } else {
                map.put(deck[i], 1);
            }
        }
        for (Integer i : map.values()) {
            min = Math.min(min, i);
        }
        if (min == 1) {
            return false;
        }
        for (Integer i : map.values()) {
            if (i%min != 0 && generateCommonDivisor(i, min) == 1) {
                return false;
            }
        }
        return true;
    }
    private int generateCommonDivisor(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a%b;
            a = t;
        }
        return a;
    }
}
