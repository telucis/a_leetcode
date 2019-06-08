package complete.dp;

import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/21
 *
 * 监控二叉树
 *
    给定一个二叉树，我们在树的节点上安装摄像头。

    节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

    计算监控树的所有节点所需的最小摄像头数量。



    示例 1：



    输入：[0,0,null,0,0]
    输出：1
    解释：如图所示，一台摄像头足以监控所有节点。
    示例 2：



    输入：[0,0,null,0,null,0,null,null,0]
    输出：2
    解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。

    提示：

    给定树的节点数的范围是 [1, 1000]。
    每个节点的值都是 0。

 */
public class binary_tree_cameras_968 {
    /**
     * dp
     */
    public int minCameraCover(TreeNode root) {
        int[] ans = dp(root);
        return Math.min(ans[1], ans[2]);
    }
    //int[0] no_covered | int[1] covered | int[2] parent_covered
    private int[] dp(TreeNode root) {
        if (root==null) return new int[]{0,0,1};
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        return new int[] {
            left[1]+right[1],
            Math.min(left[2]+Math.min(right[1], right[2]), right[2]+Math.min(left[1], left[2])),
            Math.min(left[0], Math.min(left[1], left[2]))+Math.min(right[0], Math.min(right[1], right[2]))+1
        };
    }

    /**
     * greedy
     */
    private int NOT_MONITED = 0;
    private int MONITED_NOCAM = 1;
    private int MONITED_WITHCAM = 2;
    private int cameras = 0;
    public int minCameraCover2(TreeNode root) {
        if (root==null) return 0;
        int top = dfs(root);
        return cameras + (top==NOT_MONITED ? 1:0);
    }
    private int dfs(TreeNode root) {
        if (root==null) return MONITED_NOCAM;
        int left = dfs(root.left), right = dfs(root.right);
        if (left==MONITED_NOCAM && right==MONITED_NOCAM) {
            return NOT_MONITED;
        } else if (left==NOT_MONITED || right==NOT_MONITED) {
            cameras++;
            return MONITED_WITHCAM;
        } else {
            return MONITED_NOCAM;
        }
    }
}
