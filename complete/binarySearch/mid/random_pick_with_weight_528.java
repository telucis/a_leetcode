package complete.binarySearch.mid;

import java.util.Random;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 按权重随机选择
 *
    给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。

    说明:

    1 <= w.length <= 10000
    1 <= w[i] <= 10^5
    pickIndex 将被调用不超过 10000 次
    示例1:

    输入:
    ["Solution","pickIndex"]
    [[[1]],[]]
    输出: [null,0]
    示例2:

    输入:
    ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
    [[[1,3]],[],[],[],[],[]]
    输出: [null,0,1,1,1,0]
    输入语法说明：

    输入是两个列表：调用成员函数名和调用的参数。Solution 的构造函数有一个参数，即数组 w。pickIndex 没有参数。输入参数是一个列表，即使参数为空，也会输入一个 [] 空列表。


 */
public class random_pick_with_weight_528 {

    class Solution {
        Random random;
        int[] wSums;
        public Solution(int[] w) {
            this.random = new Random();
            for (int i=1; i<w.length; ++i) {
                w[i] += w[i-1];
            }
            this.wSums = w;
        }

        public int pickIndex() {
            int len = wSums.length;
            int idx = random.nextInt(wSums[len-1])+1;
            int left=0, right=len-1;
            while (left<=right) {
                int mid = right-(right-left)/2;
                if (wSums[mid] == idx) {
                    return mid;
                } else if (wSums[mid]<idx) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
            return left;
        }
    }
}
