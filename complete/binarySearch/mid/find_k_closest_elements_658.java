package complete.binarySearch.mid;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 找到 K 个最接近的元素
 *
    给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。

    示例 1:

    输入: [1,2,3,4,5], k=4, x=3
    输出: [1,2,3,4]


    示例 2:

    输入: [1,2,3,4,5], k=4, x=-1
    输出: [1,2,3,4]


    说明:

    k 的值为正数，且总是小于给定排序数组的长度。
    数组不为空，且长度不超过 104
    数组里的每个元素与 x 的绝对值不超过 104


    更新(2017/9/19):
    这个参数 arr 已经被改变为一个整数数组（而不是整数列表）。 请重新加载代码定义以获取最新更改。


 */
public class find_k_closest_elements_658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);
        if (index<0) {
            index = -(index+1);
        }
        int i=index-1, j=index;
        while (k-- > 0) {
            if (i<0 || (j<arr.length && Math.abs(arr[i]-x) > Math.abs(arr[j]-x))) {
                j++;
            } else {
                i--;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int l=i+1; l<j; l++) {
            ans.add(arr[l]);
        })
    }
}
