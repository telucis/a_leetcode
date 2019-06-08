package complete.tree.bst_mid;

import java.util.HashMap;

/**
 * @author karl.wy
 * @date 2019/05/09
 *
 * 不同的二叉搜索树
 *
    给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

    示例:

    输入: 3
    输出: 5
    解释:
    给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

    1         3     3      2      1
     \       /     /      / \      \
      3     2     1      1   3      2
     /     /       \                 \
    2     1         2                 3

 */
public class unique_binary_search_trees_96 {
    HashMap<Integer, Integer> cache = new HashMap<>();
    public int numTrees(int n) {
        int[] nn = new int[n];
        for (int i=0; i<n; i++) {
            nn[i] = i+1;
        }
        int ans = helper(nn, 0, n-1);
        return ans;
    }
    private int helper(int[] n, int start, int end) {
        if (cache.get(end-start)!=null) {
            return cache.get(end-start);
        }
        if (start==end) {
            return 1;
        }
        int res = 0;
        for (int i=start; i<=end; i++) {
            if (i==start) {
                res += helper(n, start+1, end);
            } else if (i==end) {
                res += helper(n, start, end-1);
            } else {
                res += helper(n, start, i-1) * helper(n, i+1, end);
            }
        }
        cache.put(end-start, res);
        return res;
    }
}
