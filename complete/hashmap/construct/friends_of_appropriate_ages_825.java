package complete.hashmap.mid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 适龄的朋友
 *
    人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。

    当满足以下条件时，A 不能给 B（A、B不为同一人）发送好友请求：

    age[B] <= 0.5 * age[A] + 7
    age[B] > age[A]
    age[B] > 100 && age[A] < 100
    否则，A 可以给 B 发送好友请求。

    注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。

    求总共会发出多少份好友请求?



    示例 1:

    输入: [16,16]
    输出: 2
    解释: 二人可以互发好友申请。
    示例 2:

    输入: [16,17,18]
    输出: 2
    解释: 好友请求可产生于 17 -> 16, 18 -> 17.
    示例 3:

    输入: [20,30,100,110,120]
    输出: 3
    解释: 好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.


    说明:

    1 <= ages.length <= 20000.
    1 <= ages[i] <= 120.

 */
public class friends_of_appropriate_ages_825 {

    public int numFriendRequests(int[] ages) {
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int age : ages) {
            count.put(age, count.getOrDefault(age, 0)+1);
        }
        for (Integer a : count.keySet()) {
            for (Integer b : count.keySet()) {
                if (request(a, b)) {
                    res += count.get(a) * (count.get(b)-(a.equals(b) ? 1 : 0));
                }
            }
        }
        return res;
    }
    private boolean request(int a, int b) {
        return !(b<=0.5*a+7 || b>a);
    }
}
