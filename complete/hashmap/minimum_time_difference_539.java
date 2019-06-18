package complete.hashmap;

import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/06
 *
 * 最小时间差
 *
    给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并已分钟数表示。


    示例 1：

    输入: ["23:59","00:00"]
    输出: 1

    备注:

    列表中时间数在 2~20000 之间。
    每个时间取值在 00:00~23:59 之间。

 */
public class minimum_time_difference_539 {

    public int findMinDifference(List<String> timePoints) {
        boolean[] list = new boolean[24*60];
        for (String time : timePoints) {
            int hours = Integer.valueOf(time.split(":")[0]);
            int minutes = Integer.valueOf(time.split(":")[1]);
            if (list[hours*60 + minutes]) {
                return 0;
            }
            list[hours*60 + minutes] = true;
        }
        int min = Integer.MAX_VALUE, pre=-1, first=-1;
        for (int i=0; i<list.length; i++) {
            if (list[i]) {
                if (pre == -1) {
                    pre = i;
                    first = i;
                    continue;
                }
                min = Math.min(i - pre, min);
                if (min == 1) {
                    return min;
                }
                pre = i;
            }
        }
        min = Math.min(min, 24*60-pre+first);
        return min;
    }
}
