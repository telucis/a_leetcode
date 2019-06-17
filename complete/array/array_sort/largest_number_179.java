package complete.array.array_sort;

import java.util.Arrays;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 最大数
 *
    给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

    示例 1:

    输入: [10,2]
    输出: 210
    示例 2:

    输入: [3,30,34,5,9]
    输出: 9534330
    说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。


 */
public class largest_number_179 {

    public String largestNumber(int[] nums) {
        String[] list = new String[nums.length];
        for (int i=0; i<list.length; i++) {
            list[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(list, (a, b)->{
            String s1=a+b, s2=b+a;
            return s2.compareTo(s1);
        });
        if (list[0].charAt(0)=='0') return "0";
        StringBuilder ans = new StringBuilder();
        for (String str : list) {
            ans.append(str);
        }
        return ans.toString();
    }
}
