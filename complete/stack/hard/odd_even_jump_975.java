package complete.stack.hard;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by telucis on 2019/6/11.
 *
 * 奇偶跳
 *
     给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。

     你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：

     在进行奇数跳跃时（如，第 1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
     在进行偶数跳跃时（如，第 2，4，6... 次跳跃），你将会跳到索引 j，使得 A[i] => A[j]，A[j] 是可能的最大值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
     （对于某些索引 i，可能无法进行合乎要求的跳跃。）
     如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。

     返回好的起始索引的数量。

      

     示例 1：

     输入：[10,13,12,14,15]
     输出：2
     解释：
     从起始索引 i = 0 出发，我们可以跳到 i = 2，（因为 A[2] 是 A[1]，A[2]，A[3]，A[4] 中大于或等于 A[0] 的最小值），然后我们就无法继续跳下去了。
     从起始索引 i = 1 和 i = 2 出发，我们可以跳到 i = 3，然后我们就无法继续跳下去了。
     从起始索引 i = 3 出发，我们可以跳到 i = 4，到达数组末尾。
     从起始索引 i = 4 出发，我们已经到达数组末尾。
     总之，我们可以从 2 个不同的起始索引（i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     示例 2：

     输入：[2,3,1,1,4]
     输出：3
     解释：
     从起始索引 i=0 出发，我们依次可以跳到 i = 1，i = 2，i = 3：

     在我们的第一次跳跃（奇数）中，我们先跳到 i = 1，因为 A[1] 是（A[1]，A[2]，A[3]，A[4]）中大于或等于 A[0] 的最小值。

     在我们的第二次跳跃（偶数）中，我们从 i = 1 跳到 i = 2，因为 A[2] 是（A[2]，A[3]，A[4]）中小于或等于 A[1] 的最大值。A[3] 也是最大的值，但 2 是一个较小的索引，所以我们只能跳到 i = 2，而不能跳到 i = 3。

     在我们的第三次跳跃（奇数）中，我们从 i = 2 跳到 i = 3，因为 A[3] 是（A[3]，A[4]）中大于或等于 A[2] 的最小值。

     我们不能从 i = 3 跳到 i = 4，所以起始索引 i = 0 不是好的起始索引。

     类似地，我们可以推断：
     从起始索引 i = 1 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     从起始索引 i = 2 出发， 我们跳到 i = 3，然后我们就不能再跳了。
     从起始索引 i = 3 出发， 我们跳到 i = 4，这样我们就到达数组末尾。
     从起始索引 i = 4 出发，我们已经到达数组末尾。
     总之，我们可以从 3 个不同的起始索引（i = 1, i = 3, i = 4）出发，通过一定数量的跳跃到达数组末尾。
     示例 3：

     输入：[5,1,3,4,2]
     输出：3
     解释：
     我们可以从起始索引 1，2，4 出发到达数组末尾。
      

     提示：

     1 <= A.length <= 20000
     0 <= A[i] < 100000
 */
public class odd_even_jump_975 {

    /**
     * dp+treeMap
     */
    public int oddEvenJumps(int[] A) {
        int n = A.length, res=1;
        boolean[] higher = new boolean[n], lower=new boolean[n];
        higher[n-1]=lower[n-1]=true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n-1], n-1);
        for (int i=n-2; i>=0; --i) {
            Map.Entry hi=map.ceilingEntry(A[i]), lo=map.floorEntry(A[i]);
            if (hi!=null) higher[i] = lower[(int)hi.getValue()];
            if (lo!=null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }

    /**
     * dp+stack 待调试
     */
    public int oddEvenJumps2(int[] A) {
        int n = A.length, res=1;
        int[] nextH=new int[n], nextL=new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<A.length; i++) {
            while (!stack.isEmpty() && A[stack.peek()]<=A[i]) {
                nextH[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack = new Stack<>();
        for (int i=0; i<A.length; i++) {
            while (!stack.isEmpty() && A[stack.peek()]>=A[i]) {
                nextL[stack.pop()] = i;
            }
            stack.push(i);
        }
        int[] h=new int[n], l=new int[n];
        h[n-1]=l[n-1]=1;
        for (int i=n-2; i>=0; --i) {
            h[i] = l[nextH[i]];
            l[i] = h[nextL[i]];
            res+=h[i];
//            System.out.println(i+" "+h[i]+" "+l[i]);
        }
        return res;
    }
}
