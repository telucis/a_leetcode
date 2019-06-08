package medium.heap;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author karl.wy
 * @date 2019/05/17
 *
 * 一手顺子
 *
    爱丽丝有一手（hand）由整数数组给定的牌。

    现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。

    如果她可以完成分组就返回 true，否则返回 false。



    示例 1：

    输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
    输出：true
    解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
    示例 2：

    输入：hand = [1,2,3,4,5], W = 4
    输出：false
    解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。


    提示：

    1 <= hand.length <= 10000
    0 <= hand[i] <= 10^9
    1 <= W <= hand.length

 */
public class hand_of_straights_846 {

    /**
     Intuition:

         Count number of different cards to a map c
         Loop from the smallest card number.
         Everytime we meet a new card i, we cut off i - i + W - 1 from the counter.

     Time Complexity:
         O(MlogM + MW), where M is the number of different cards.

     */
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length%W!=0) return false;
        Map<Integer, Integer> c = new TreeMap<>();
        for (int i : hand) c.put(i, c.getOrDefault(i, 0)+1);
        for (int it : c.keySet()) {
            if (c.get(it) > 0) {
                for (int i=W-1; i>=0; --i) {
                    if (c.getOrDefault(it+i, 0) < c.get(it)) return false;
                    c.put(it+i, c.get(it+i)-c.get(it));
                }
            }
        }
        return true;
    }
}
