package complete.tree.divideConquer_mid;

import dataStruct.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/08
 *
 * 所有可能的满二叉树
 *
    满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。

    返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。

    答案中每个树的每个结点都必须有 node.val=0。

    你可以按任何顺序返回树的最终列表。



    示例：

    输入：7
    输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
    解释：



    提示：

    1 <= N <= 20

 */
public class all_possible_full_binary_trees_894 {
    Map<Integer, List<TreeNode>> cache = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        if (N%2==0) {
            return ans;
        }
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        if (N==1) {
            ans.add(new TreeNode(0));
            return ans;
        }
        N = N-1;
        for (int i=1; i<N; i+=2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N-i);
            for (TreeNode nl : left) {
                for (TreeNode nr : right) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = nl;
                    cur.right = nr;
                    ans.add(cur);
                }
            }
        }
        cache.put(N, ans);
        return ans;
    }
}
