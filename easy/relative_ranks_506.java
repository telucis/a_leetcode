package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 相对名次
 *
    给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。

    (注：分数越高的选手，排名越靠前。)

    示例 1:

    输入: [5, 4, 3, 2, 1]
    输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
    解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
    余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
    提示:

    N 是一个正整数并且不会超过 10000。
    所有运动员的成绩都不相同。

 */
public class relative_ranks_506 {

    public String[] findRelativeRanks(int[] nums) {
        ArrayList<String> list = new ArrayList<>();
        int[] order = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            int count = 0;
            for (int j=0; j<nums.length; j++) {
                if (nums[j]>nums[i]) {
                    count++;
                }
            }
            order[i] = count;
        }
        for (int k=0; k<nums.length; k++) {
            if(order[k]==0) {
                list.add("Gold Medal");
            } else if (order[k] == 1) {
                list.add("Silver Medal");
            } else if (order[k] == 2) {
                list.add("Bronze Medal");
            } else {
                list.add(String.valueOf(order[k]+1));
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
