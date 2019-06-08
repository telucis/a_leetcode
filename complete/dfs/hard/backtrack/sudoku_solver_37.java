package complete.dfs.hard.backtrack;

/**
 * @author karl.wy
 * @date 2019/05/24
 *
 * 解数独
 *
    编写一个程序，通过已填充的空格来解决数独问题。

    一个数独的解法需遵循如下规则：

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
    空白格用 '.' 表示。



    一个数独。



    答案被标成红色。

    Note:

    给定的数独序列只包含数字 1-9 和字符 '.' 。
    你可以假设给定的数独只有唯一解。
    给定数独永远是 9x9 形式的。

 */
public class sudoku_solver_37 {

    public void solveSudoku(char[][] board) {
        if (board==null || board.length==0 || board[0].length==0) return;
        dfs(board);
    }
    private boolean dfs(char[][] board) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j]=='.') {
                    for (char c='1'; c<='9'; c++) {
                        if (valid(board, i, j, c)) {
                            board[i][j]=c;
                            if (dfs(board)) return true;
                            else board[i][j]='.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private boolean valid(char[][] board, int x, int y, char c) {
        for (int i=0; i<9; i++) if (board[i][y]==c) return false;
        for (int i=0; i<9; i++) if (board[x][i]==c) return false;
        for (int i=0; i<9; i++) {
            int numX=x/3, numY=y/3, row=numX*3+i/3, col=numY*3+i%3;
            if (board[row][col]==c) return false;
        }
        return true;
    }
}
