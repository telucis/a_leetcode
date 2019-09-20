

public enum Role {
    Red, Black;
}

public class Pos {
    private int x;
    private int y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isSame(int r, int c) {
        return x==r && y==c;
    }
}

public abstract class Chess {
    protected String name;
    protected boolean isValid;
    protected Pos pos;
    protected Role role;
    public abstract void move(Pos pos);
}

public class King extends Chess {
    public void move(Pos pos) {

    }
}

public abstract class Board<T extends Chess> {
    protected Chess[][] board;
    public Board(int row, int col) {
        board = new Chess[row][col];
    }
    public abstract void initial();
}

public class ChineseBoard extends Board<Chess> {
    public void initial() {}
    public ChineseBoard(int row, int col) {
        super(row, col);
    }
}

public class Hand<T extends Chess> {
    
}

public class ChineseHand extends Hand<> {
    Role role;
    public ChineseHand(Role role) {
        this.role = role;
    }
}

public abstract class Game {
    protected List<Hand> players;
    protected Hand currentPlayer;
    protected boolean gameEnd;
    public abstract initial();
}

public class ChineseChess implements Game {
    public Board board;
    public List<Hand> players;
    public ChineseChess() {
        players = new ArrayList<>(2);
        players.add(new ChineseHand(Role.Red));
        players.add(new ChineseHand(Role.Black));

    }

    public move(Hand hand, Chess chess, Integer x, Integer y) {
        if (hand != currentPlayer) throw new NoTurnPlayer();

    }

    public initialChess() {

    }

    public initialGame() {

    }
}

public class NoTurnPlayer extends Exception {
    public NoTurnPlayer() {
        super("NoTurnPlayer");
    }
}

