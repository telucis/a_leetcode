package complete.array.subArray.preSum.easy;

import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/24
 *
 * 公平的糖果交换
 *
    爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。

    因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

    返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。

    如果有多个答案，你可以返回其中任何一个。保证答案存在。



    示例 1：

    输入：A = [1,1], B = [2,2]
    输出：[1,2]
    示例 2：

    输入：A = [1,2], B = [2,3]
    输出：[1,2]
    示例 3：

    输入：A = [2], B = [1,3]
    输出：[2,3]
    示例 4：

    输入：A = [1,2,5], B = [2,4]
    输出：[5,4]


    提示：

    1 <= A.length <= 10000
    1 <= B.length <= 10000
    1 <= A[i] <= 100000
    1 <= B[i] <= 100000
    保证爱丽丝与鲍勃的糖果总量不同。
    答案肯定存在。

 */
public class fair_candy_swap_888 {

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        HashSet<Integer> setA = new HashSet<>(), setB = new HashSet<>();
        for (int i=0; i<A.length; i++) {
            sumA += A[i];
            setA.add(A[i]);
        }
        for (int i=0; i<B.length; i++) {
            sumB += B[i];
            setB.add(B[i]);
        }
        int diff = (sumB-sumA)/2;
        for (Integer item : setA) {
            if (setB.contains(item+diff)) {
                return new int[]{item, item+diff};
            }
        }
        return new int[]{-1,-1};
    }
}
