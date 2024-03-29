package complete.tree.quadTree;

import dataStruct.QuadTreeNode;

/**
 * @author karl.wy
 * @date 2019/04/28
 *
 * 建立四叉树
 *
    我们想要使用一棵四叉树来储存一个 N x N 的布尔值网络。网络中每一格的值只会是真或假。树的根结点代表整个网络。对于每个结点, 它将被分等成四个孩子结点直到这个区域内的值都是相同的.

    每个结点还有另外两个布尔变量: isLeaf 和 val。isLeaf 当这个节点是一个叶子结点时为真。val 变量储存叶子结点所代表的区域的值。

    你的任务是使用一个四叉树表示给定的网络。下面的例子将有助于你理解这个问题：

    给定下面这个8 x 8 网络，我们将这样建立一个对应的四叉树：



    由上文的定义，它能被这样分割：





    对应的四叉树应该像下面这样，每个结点由一对 (isLeaf, val) 所代表.

    对于非叶子结点，val 可以是任意的，所以使用 * 代替。



    提示：

    N 将小于 1000 且确保是 2 的整次幂。
    如果你想了解更多关于四叉树的知识，你可以参考这个 wiki 页面。

 */
public class construct_quad_tree_427 {

    public QuadTreeNode construct(int[][] grid) {
        return getQuad(grid, 0, 0, grid.length);
    }
    private QuadTreeNode getQuad(int[][] grid, int x, int y, int offset) {

        for (int i=x; i<x+offset; i++) {
            for (int j=y; j<y+offset; j++) {
                if (grid[i][j] != grid[x][y]) {
                    return new QuadTreeNode(false, false,
                        getQuad(grid, x, y, offset/2),
                        getQuad(grid, x, y+offset/2, offset/2),
                        getQuad(grid, x+offset/2, y, offset/2),
                        getQuad(grid, x+offset/2, y+offset/2, offset/2)
                        );
                }
            }
        }
        return new QuadTreeNode(grid[x][y]==1, true, null, null, null, null);
    }
}
