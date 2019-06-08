package medium.array.onePass;

import sun.plugin2.util.AppletEnumeration;

/**
 * Created by telucis on 2019/5/12.
 *
 * 数组中的最长山脉
 *
     我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

     B.length >= 3
     存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     （注意：B 可以是 A 的任意子数组，包括整个数组 A。）

     给出一个整数数组 A，返回最长 “山脉” 的长度。

     如果不含有 “山脉” 则返回 0。



     示例 1：

     输入：[2,1,4,7,3,2,5]
     输出：5
     解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
     示例 2：

     输入：[2,2,2]
     输出：0
     解释：不含 “山脉”。


     提示：

     0 <= A.length <= 10000
     0 <= A[i] <= 10000

 */
public class longest_mountain_in_array_845 {

    public int longestMountain(int[] A) {
        int ans=0, start=0, flag=0;
        for (int i=1; i<A.length; i++) {
            if (flag==0) {
                if (A[i]>A[i-1]) {
                    flag=1;
                    start=i;
                }
            } else if (flag==1) {
                if (A[i]<A[i-1]) {
                    flag=2;
                } else if (A[i]==A[i-1]) {
                    flag=0;
                }
            } else {
                if (A[i]>A[i-1]) {
                    ans = Math.max(ans, i-start+1);
                    flag=1;
                    start=i-1;
                } else if (A[i]==A[i-1]) {
                    ans = Math.max(ans, i-start+1);
                    flag=0;
                }
            }
        }
        if (flag==2) {
            ans = Math.max(ans, A.length-start);
        }
        return ans;
    }
}
