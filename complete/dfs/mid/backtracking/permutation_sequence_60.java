package complete.dfs.mid.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 第k个排列
 *
    给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

    按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    给定 n 和 k，返回第 k 个排列。

    说明：

    给定 n 的范围是 [1, 9]。
    给定 k 的范围是[1,  n!]。
    示例 1:

    输入: n = 3, k = 3
    输出: "213"
    示例 2:

    输入: n = 4, k = 9
    输出: "2314"

 */
public class permutation_sequence_60 {
    /**
     * permutations
     */
    int index;
    String ans="";
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=n; i++){
            list.add(i);
        }
        index = k;
        helper(list, new LinkedList<>());
        return ans;
    }
    private boolean helper(List<Integer> list, LinkedList<Integer> tmp) {
        if (list.size()==0) {
            if (index==1) {
                for (Integer i : tmp) ans+=String.valueOf(i);
                return true;
            }
            index--;
            return false;
        }
        for (int i=0; i<list.size(); i++) {
            int num = list.get(i);
            tmp.add(num);
            list.remove(i);
            if (helper(list, tmp)) return true;
            tmp.removeLast();
            list.add(i, num);
        }
        return false;
    }
}
