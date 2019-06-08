package complete.dfs.mid.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 字典序排数
 *
    给定一个整数 n, 返回从 1 到 n 的字典顺序。

    例如，

    给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。

    请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。


 */
public class lexicographical_numbers_386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i=1; i<10; i++) {
            dfs(i, n, ans);
        }
        return ans;
    }
    private void dfs(int num, int n, List<Integer> ans) {
        if (num>n) return;
        ans.add(num);
        for (int i=0; i<10; i++) {
            dfs(num*10+i, n, ans);
        }
    }
}
