package complete.dfs.mid.traversal;

/**
 * @author karl.wy
 * @date 2019/05/05
 *
 * 单词搜索
 *
    给定一个二维网格和一个单词，找出该单词是否存在于网格中。

    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    示例:

    board =
    [
    ['A','B','C','E'],
    ['S','F','C','S'],
    ['A','D','E','E']
    ]

    给定 word = "ABCCED", 返回 true.
    给定 word = "SEE", 返回 true.
    给定 word = "ABCB", 返回 false.

 */
public class word_search_79 {

    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (exist(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean exist(char[][] board, int x, int y, char[] w, int i) {
        if (i == w.length) {
            return true;
        }
        if (x<0 || y<0 || x>=board.length || y>=board[0].length) {
            return false;
        }
        if (w[i] != board[x][y]) {
            return false;
        }
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        board[x][y] ^= 256;
        boolean res = false;
        for (int k=0; k<4; k++) {
            if (exist(board, x+dx[k], y+dy[k], w, i+1)) {
                res = true;
                break;
            }
        }
        board[x][y] ^= 256;
        return res;
    }
}
