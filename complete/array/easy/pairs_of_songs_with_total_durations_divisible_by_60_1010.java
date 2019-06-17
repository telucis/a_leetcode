package complete.array.easy;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 总持续时间可被 60 整除的歌曲
 *
    在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。

    返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。



    示例 1：

    输入：[30,20,150,100,40]
    输出：3
    解释：这三对的总持续时间可被 60 整数：
    (time[0] = 30, time[2] = 150): 总持续时间 180
    (time[1] = 20, time[3] = 100): 总持续时间 120
    (time[1] = 20, time[4] = 40): 总持续时间 60
    示例 2：

    输入：[60,60,60]
    输出：3
    解释：所有三对的总持续时间都是 120，可以被 60 整数。


    提示：

    1 <= time.length <= 60000
    1 <= time[i] <= 500

 */
public class pairs_of_songs_with_total_durations_divisible_by_60_1010 {

    public int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i=0; i<time.length; i++) {
            if (map.containsKey(time[i]%60)) {
                res += map.get(time[i]%60);
            }
            int key = (60-time[i]%60)%60;
            if (map.containsKey(key)) {
                map.put(key, map.get(key)+1);
            } else {
                map.put(key, 1);
            }
        }
        return res;
    }
}
