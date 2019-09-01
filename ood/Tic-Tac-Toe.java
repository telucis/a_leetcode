/**
 * 设计井字棋游戏。
 * 
 * 棋盘的尺寸为3
 * X 总是先行动走出第一步
 * 如果一个位置已经被占，且一名玩家打算占领该位置，一个AlreadyTakenException信息将被抛出
 * 如果一名玩家胜利，且有玩家打算继续行动，一个GameEndException信息将被抛出
 * 
 */

public class TicTacToe {
    private char[][] board;
    private char currentPlayerMark;
    private boolean gameEnd;

    public TicTacToe() {
        board = new char[3][3];
        initialze();
    }

    private void initialze() {
        gameEnd = false;
        currentPlayerMark = 'X';
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {
        if (gameEnd) throw new GameEndException();
        if (board[row][col] != '-') throw new AlreadyTakenException();

        board[row][col] = currentPlayerMark();

        boolean win = true;
        for (int i=0; i<board.length; i++) {
            if (board[row][i] != currentPlayerMark) {
                win = false;
                break;
            }
        }
        if (win) {
            gameEnd = true;
            return win;
        }
        win = true;
        for (int i=0; i<board.length; i++) {
            if (board[i][col] != currentPlayerMark) {
                win = false;
                break;
            }
        }
        if (win) {
            gameEnd = true;
            return win;
        }
        win = true;
        for (int i=0; i<board.length; i++) {
            if (board[i][i] != currentPlayerMark) {
                win = false;
                break;
            }
        }
        if (win) {
            gameEnd = true;
            return win;
        }
        win = true;
        for (int i=0; i<board.length; i++) {
            if (board[i][board.length-i-1] != currentPlayerMark) {
                win = false;
                break;
            }
        }
        if (win) {
            gameEnd = true;
            return win;
        }
        changePlayer();
        return win;
    }

    public void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        } else {
            currentPlayerMark = 'X';
        }
    }

    class GameEndException implements Exception {
        public GameEndException() {
            super("Game has been ended, cannot make any more moves");
        }
    }

    class AlreadyTakenException implements Exception {
        public AlreadyTakenException() {
            super("This place has been taken");
        }
    }
}