package cpp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;


class GameTest {
    // A scripted player returns a predefined sequence of moves
    static class ScriptedPlayer extends Player {
        private final Deque<Move> script = new ArrayDeque<>();
        ScriptedPlayer(Mark mark, Move... moves) {
            super(mark);
            for (Move m : moves) script.addLast(m);
        }
        @Override public Move nextMove(Board board) {
            if (script.isEmpty()) throw new IllegalStateException("No scripted moves left");
            return script.removeFirst();
        }
    }


    @Test
    @DisplayName("Scripted game where X wins")
    void scriptedGameXWins() {
        Board b = new Board(3);
        // X moves: (0,0), (0,1), (0,2) → row win
        ScriptedPlayer x = new ScriptedPlayer(Mark.X,
                new Move(0,0, Mark.X), new Move(0,1, Mark.X), new Move(0,2, Mark.X));
        // O moves: (1,0), (1,1)
        ScriptedPlayer o = new ScriptedPlayer(Mark.O,
                new Move(1,0, Mark.O), new Move(1,1, Mark.O));


        Game g = new Game(b, x, o);
        Optional<Mark> w = g.run();
        Assertions.assertEquals(Optional.of(Mark.X), w);
    }

    @Test
    @DisplayName("Invalid moves are retried without crashing the game")
    void invalidMoveIsRetried() {
        Board b = new Board(3);
        // X: provide enough valid moves to finish the game
        // After O finally plays a valid move, X will continue and eventually win the top row
        ScriptedPlayer x = new ScriptedPlayer(Mark.X,
                new Move(0,0, Mark.X),
                new Move(0,1, Mark.X),
                new Move(0,2, Mark.X));


        // O: two invalid attempts followed by valid moves
        // 1) wrong mark (X) → invalid, retried without swapping turn
        // 2) occupied (0,0) → invalid, retried without swapping turn
        // 3) valid (1,1)
        // 4) valid (1,0) — this still won't block X's row win
        ScriptedPlayer o = new ScriptedPlayer(Mark.O,
                new Move(0,1, Mark.X),
                new Move(0,0, Mark.O),
                new Move(1,1, Mark.O),
                new Move(1,0, Mark.O));


        Game g = new Game(b, x, o);
        Optional<Mark> result = g.run();
        // The key assertion: game completes and X wins; invalid O moves did not crash the loop
        Assertions.assertEquals(Optional.of(Mark.X), result);
    }
}