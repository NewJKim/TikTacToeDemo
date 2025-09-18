package cpp;

import java.util.Optional;


public class Game {
    private final Board board;
    private Player current;
    private Player other;


    public Game(Board board, Player p1, Player p2) {
        if (board == null || p1 == null || p2 == null) throw new IllegalArgumentException("null arg");
        this.board = board;
        // Ensure current player matches board's starting turn (X starts)
        if (p1.getMark() == board.getCurrentTurn()) {
            this.current = p1; this.other = p2;
        } else {
            this.current = p2; this.other = p1;
        }
    }


    public Optional<Mark> run() {
        while (true) {
            Move mv = current.nextMove(board);
            try {
                board.place(mv);
            } catch (IllegalArgumentException ex) {
// If a Player returns an invalid move, ask it again (robustness)
                continue;
            }
            Optional<Mark> w = board.winner();
            if (w.isPresent()) return w;
            if (board.isFull()) return Optional.empty();
            swapPlayers();
        }
    }


    private void swapPlayers() {
        Player tmp = current; current = other; other = tmp;
    }
}