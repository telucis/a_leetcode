package complete.dp.backpack;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 组合总和 Ⅳ
 *
    给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

    示例:

    nums = [1, 2, 3]
    target = 4

    所有可能的组合为：
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)

    请注意，顺序不同的序列被视作不同的组合。

    因此输出为 7。
    进阶：
    如果给定的数组中含有负数会怎么样？
    问题会产生什么变化？
    我们需要在题目中添加什么限制来允许负数的出现？

    致谢：
    特别感谢 @pbrother 添加此问题并创建所有测试用例。


 */
public class combination_sum_iv_377 {

    /**
     * 01 backpack
     * dp[target+1]
     * for(target+1)for(nums) dp[i]+=res[i-num]
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] res = new int[target + 1];
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (num > i)
                    break;
                else if (num == i)
                    res[i] += 1;
                else
                    res[i] += res[i-num];
            }
        }
        return res[target];
    }

    /**
     * memory search
     */
    public int combinationSum4_2(int[] nums, int target) {
        Arrays.sort(nums);
        return dfs(nums, target, new HashMap<Integer, Integer>());
    }
    private int dfs(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target==0) return 1;
        if (map.containsKey(target)) return map.get(target);
        int count = 0;
        for (int i=0; i<nums.length; i++) {
            if (target<nums[i]) continue;
            count += dfs(nums, target-nums[i], map);
        }
        map.put(target, count);
        return count;
    }
}
