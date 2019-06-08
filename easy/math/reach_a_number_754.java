package easy.math;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 到达终点数字
 *
    在一根无限长的数轴上，你站在0的位置。终点在target的位置。

    每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。

    返回到达终点需要的最小移动次数。

    示例 1:

    输入: target = 3
    输出: 2
    解释:
    第一次移动，从 0 到 1 。
    第二次移动，从 1 到 3 。
    示例 2:

    输入: target = 2
    输出: 3
    解释:
    第一次移动，从 0 到 1 。
    第二次移动，从 1 到 -1 。
    第三次移动，从 -1 到 2 。
    注意:

    target是在[-10^9, 10^9]范围中的非零整数。

 */
public class reach_a_number_754 {
    /**
     简单题中的极品。 。。。 首先由于对称性，target是正是负影响不大。

     因为比如达到target=2=1-2+3.

     如果是-2，那就是-2=-1+2-3

     所以相当于是完全对称的一个选择。

     那么不妨设这个target是正的（用abs函数）

     所以我们尽量往右移动就可以达到目的地。

     假设1+2+3+...+k=sum

     如果sum=target，毫无疑问那么k就是最终答案。#1

     如果sum>target，而且sum-target是一个偶数，那么我们可以翻转一个数字的符号来完成等式。

     比如sum-target=4，那么我们把2变成-2，那么sum减小了4.

     这是由于（1+2+3+...k）-（1-2+3...k）=4

     也就是可以归结为：

     当sum-target为偶数，1+...-（sum-target）/2+...+k=target，那么答案依然是k。#2

     当sum-target为奇数，那么sum-target+1是一个偶数

     类似#2的证明，1+...-(sum-target+1)/2+...k=target-1

     此时再考虑k的奇偶性。

     如果k是偶数并且k>sum-target+1

     那么1+...-(sum-target+1)/2+....-(k/2)...+k+(k+1)=target

     由#2相似可证，相当于在1+2....+k+(k+1)减去了sum-target+1和k。

     等价于sum+（k+1）-sum+target-1-k====>target也就是答案是k+1.#3

     如果k=sum-target+1，由#3可知依然是k+1.#4

     如果k是奇数：

     1+2+...-(sum-target+1)/2.....+k-(k+1)+(k+2)=sum-(sum-target+1)+1=target,

     因此答案是k+2.#5
     */
    public int reachNumber(int target) {
        int t = Math.abs(target);
        int s = 0;
        int dis = 0;
        while (dis < t) {
            s++;
            dis+=s;
        }
        int dt = dis - t;
        if (dt%2==0) {
            return s;
        } else {
            if (s%2==0) {
                return s+1;
            } else {
                return s+2;
            }
        }
    }
}
